package com.example.Employee.service;

import com.example.Employee.dto.EmployeeRequestDto;
import com.example.Employee.dto.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    // Create Employee
    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto);

    // Get All Employees
    List<EmployeeResponseDto> getAllEmployees();

    // Get Employee By ID
    EmployeeResponseDto getEmployeeById(UUID id);

    // Get Employee By Email
    EmployeeResponseDto getEmployeeByEmail(String email);

    // Get Employee By Employee Code
    EmployeeResponseDto getEmployeeByCode(String employeeCode);

    // Update Employee
    EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto employeeRequestDto);

    // Delete Employee
    void deleteEmployee(UUID id);
}