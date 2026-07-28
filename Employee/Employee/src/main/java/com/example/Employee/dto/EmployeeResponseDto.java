package com.example.Employee.dto;


import com.example.Employee.enums.EmploymentStatus;
import com.example.Employee.enums.JobTitle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private JobTitle jobTitle;

    private UUID departmentId;
    private String departmentName;

    private String managerName;

    private LocalDate dateOfBirth;
    private LocalDate joiningDate;

    private EmploymentStatus employmentStatus;
    private Double salary;

    private String address;
    private String city;
    private String state;
    private String country;


    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}