package com.example.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    private String departmentName;

    private String departmentCode;

    private String location;

    private String description;
}