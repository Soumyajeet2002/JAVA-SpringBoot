package com.example.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

    private UUID id;

    private String departmentName;

    private String departmentCode;

    private String location;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}