package com.tec.attendenceTracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.attendenceTracker.dto.EmployeeDTO;
import com.tec.attendenceTracker.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from test endpoint");
        response.put("status", "success");
        response.put("count", 123);
        response.put("isActive", true);

        return response;
    }


    // Create Employee
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<EmployeeDTO> createEmployee(
            @RequestPart("employee") String employeeJson,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) {

        try {
            EmployeeDTO employeeDTO = objectMapper.readValue(employeeJson, EmployeeDTO.class);
            employeeDTO.setProfilePicture(profilePicture);
            EmployeeDTO created = employeeService.createEmployee(employeeDTO);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid employee data: " + e.getMessage());
        }
    }

    // Get all Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // Update Employee
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestPart("employee") String employeeJson,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) {

        try {
            EmployeeDTO employeeDTO = objectMapper.readValue(employeeJson, EmployeeDTO.class);
            employeeDTO.setProfilePicture(profilePicture);
            EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid employee update data: " + e.getMessage());
        }
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
