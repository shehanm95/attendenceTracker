package com.tec.attendenceTracker.repository;

import com.tec.attendenceTracker.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
