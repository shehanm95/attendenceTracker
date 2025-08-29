package com.tec.attendenceTracker.service.impl;

import com.tec.attendenceTracker.dto.EmployeeDTO;
import com.tec.attendenceTracker.mapper.EmployeeMapper;
import com.tec.attendenceTracker.models.Address;
import com.tec.attendenceTracker.models.Employee;
import com.tec.attendenceTracker.repository.AddressRepository;
import com.tec.attendenceTracker.repository.EmployeeRepository;
import com.tec.attendenceTracker.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final EmployeeMapper employeeMapper;

    private final Path uploadDir = Paths.get("uploads/profile-pictures");

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               AddressRepository addressRepository,
                               EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.employeeMapper = employeeMapper;

        // Ensure upload directory exists
        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        handleAddressPersistence(dto);
        saveProfilePicture(dto);

        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        handleAddressPersistence(dto);
        if (dto.getProfilePicture() != null && !dto.getProfilePicture().isEmpty()) {
            saveProfilePicture(dto);
        }

        employeeMapper.updateEntity(existing, dto);
        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toDto(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    // --- Helper methods ---

    private void handleAddressPersistence(EmployeeDTO dto) {
        if (dto.getPermanentAddress() != null && dto.getPermanentAddress().getId() == null) {
            Address savedPermanent = addressRepository.save(
                    employeeMapper.toEntity(dto).getPermanentAddress());
            dto.getPermanentAddress().setId(savedPermanent.getId());
        }

        if (dto.getCurrentAddress() != null && dto.getCurrentAddress().getId() == null) {
            Address savedCurrent = addressRepository.save(
                    employeeMapper.toEntity(dto).getCurrentAddress());
            dto.getCurrentAddress().setId(savedCurrent.getId());
        }
    }

    private void saveProfilePicture(EmployeeDTO dto) {
        MultipartFile file = dto.getProfilePicture();
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path targetPath = uploadDir.resolve(fileName);
            try {
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                dto.setProfilePicturePath(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save profile picture", e);
            }
        }
    }
}
