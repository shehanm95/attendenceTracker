package com.tec.attendenceTracker.mapper;

import com.tec.attendenceTracker.dto.EmployeeDTO;
import com.tec.attendenceTracker.models.Employee;

public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO dto);

    void updateEntity(Employee existing, EmployeeDTO dto);
}
