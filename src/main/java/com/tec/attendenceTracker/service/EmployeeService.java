package com.tec.attendenceTracker.service;

import com.tec.attendenceTracker.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
