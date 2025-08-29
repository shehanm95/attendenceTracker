package com.tec.attendenceTracker.mapper.impl;

import com.tec.attendenceTracker.dto.EmployeeDTO;
import com.tec.attendenceTracker.mapper.AddressMapper;
import com.tec.attendenceTracker.mapper.EmployeeMapper;
import com.tec.attendenceTracker.models.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    private final AddressMapper addressMapper;

    public EmployeeMapperImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setPersonalPhone(employee.getPersonalPhone());
        dto.setPersonalEmail(employee.getPersonalEmail());
        dto.setEmergencyContactName(employee.getEmergencyContactName());
        dto.setEmergencyContactPhone(employee.getEmergencyContactPhone());

        dto.setPermanentAddress(addressMapper.toDto(employee.getPermanentAddress()));
        dto.setCurrentAddress(addressMapper.toDto(employee.getCurrentAddress()));

        dto.setProfilePicturePath(employee.getProfilePicturePath());

        dto.setOfficialEmail(employee.getOfficialEmail());
        dto.setOfficialPhone(employee.getOfficialPhone());
        dto.setDesignation(employee.getDesignation());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPersonalPhone(dto.getPersonalPhone());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setEmergencyContactName(dto.getEmergencyContactName());
        employee.setEmergencyContactPhone(dto.getEmergencyContactPhone());

        employee.setPermanentAddress(addressMapper.toEntity(dto.getPermanentAddress()));
        employee.setCurrentAddress(addressMapper.toEntity(dto.getCurrentAddress()));

        employee.setProfilePicturePath(dto.getProfilePicturePath());

        employee.setOfficialEmail(dto.getOfficialEmail());
        employee.setOfficialPhone(dto.getOfficialPhone());
        employee.setDesignation(dto.getDesignation());
        employee.setDepartment(dto.getDepartment());

        return employee;
    }

    @Override
    public void updateEntity(Employee existing, EmployeeDTO dto) {
        if (existing == null || dto == null) return;

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPersonalPhone(dto.getPersonalPhone());
        existing.setPersonalEmail(dto.getPersonalEmail());
        existing.setEmergencyContactName(dto.getEmergencyContactName());
        existing.setEmergencyContactPhone(dto.getEmergencyContactPhone());

        if (dto.getPermanentAddress() != null && existing.getPermanentAddress() != null) {
            addressMapper.updateEntity(existing.getPermanentAddress(), dto.getPermanentAddress());
        }

        if (dto.getCurrentAddress() != null && existing.getCurrentAddress() != null) {
            addressMapper.updateEntity(existing.getCurrentAddress(), dto.getCurrentAddress());
        }

        if (dto.getProfilePicturePath() != null) {
            existing.setProfilePicturePath(dto.getProfilePicturePath());
        }

        // Official fields should not be updated here (view-only)
    }
}
