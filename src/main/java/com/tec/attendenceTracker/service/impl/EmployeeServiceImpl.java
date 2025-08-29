package com.tec.attendenceTracker.service.impl;

import com.tec.attendenceTracker.dto.EmployeeDTO;
import com.tec.attendenceTracker.mapper.EmployeeMapper;
import com.tec.attendenceTracker.models.Employee;
import com.tec.attendenceTracker.repository.EmployeeRepository;
import com.tec.attendenceTracker.service.EmployeeService;
import com.tec.attendenceTracker.service.ImageService;
import org. springframework.core.io.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ImageService imageService;
    private final EmployeeMapper employeeMapper;

    private static final String IMAGE_DIR = "profilePictures/";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, ImageService imageService) {
        this.employeeRepository = employeeRepository;
        this.imageService = imageService;
        this.employeeMapper = employeeMapper;
    }


    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        validateRequiredFields(dto);

        Employee employee;
        Optional<Employee> existing = employeeRepository.findById(1L);

        if (existing.isPresent()) {
            employee = existing.get();
            employeeMapper.updateEntity(employee, dto);
        } else {
            employee = employeeMapper.toEntity(dto);
          //  employee.setId(1L);  // new entity with ID=1
        }

        // Set official email and phone
        employee.setOfficialEmail(dto.getFirstName().toLowerCase() + "@office.com");
        employee.setOfficialPhone("0729763093");

        // Save profile picture if present
        MultipartFile image = dto.getProfilePicture();
        if (image != null && !image.isEmpty()) {
            String savedName = imageService.saveImage(IMAGE_DIR, String.valueOf(System.currentTimeMillis()), image);
            employee.setProfilePicturePath(savedName);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(emp -> {
            EmployeeDTO dto = new EmployeeDTO();
            BeanUtils.copyProperties(emp, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        validateRequiredFields(dto);

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        BeanUtils.copyProperties(dto, existing, "id", "profilePicturePath");

        MultipartFile newImage = dto.getProfilePicture();
        if (newImage != null && !newImage.isEmpty()) {
            // delete old image
            if (existing.getProfilePicturePath() != null) {
                imageService.deleteImage(IMAGE_DIR, existing.getProfilePicturePath());
            }
            // save new image
            String savedName = imageService.saveImage(IMAGE_DIR, String.valueOf(id), newImage);
            existing.setProfilePicturePath(savedName);
        }

        employeeRepository.save(existing);

        EmployeeDTO updatedDto = new EmployeeDTO();
        BeanUtils.copyProperties(existing, updatedDto);
        return updatedDto;
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }

        Employee employee = optional.get();

        if (employee.getProfilePicturePath() != null) {
            imageService.deleteImage(IMAGE_DIR, employee.getProfilePicturePath());
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Resource> getImage(String fileName) {
        return imageService.getImage(IMAGE_DIR, fileName);
    }

    // ✅ Internal validation method
    private void validateRequiredFields(EmployeeDTO dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (dto.getDesignation() == null || dto.getDesignation().isBlank()) {
            throw new IllegalArgumentException("Designation is required");
        }
        if (dto.getDepartment() == null || dto.getDepartment().isBlank()) {
            throw new IllegalArgumentException("Department is required");
        }

        if (dto.getPersonalPhone() != null && !dto.getPersonalPhone().matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Personal phone must be 10 digits");
        }

        if (dto.getEmergencyContactPhone() != null && !dto.getEmergencyContactPhone().matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Emergency contact phone must be 10 digits");
        }

        if (dto.getPersonalEmail() != null && !dto.getPersonalEmail().matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid personal email format");
        }
    }
}
