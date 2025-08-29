package com.tec.attendenceTracker.service;

import com.tec.attendenceTracker.dto.EmployeeDTO;

import org. springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    ResponseEntity<Resource> getImage(String fileName);
}
