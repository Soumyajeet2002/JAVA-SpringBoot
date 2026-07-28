package com.example.Employee.mapper;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;
import com.example.Employee.entities.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequestDto dto) {

        Department department = new Department();

        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setLocation(dto.getLocation());
        department.setDescription(dto.getDescription());

        return department;
    }

    public DepartmentResponseDto toResponseDto(Department department) {

        DepartmentResponseDto dto = new DepartmentResponseDto();

        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setLocation(department.getLocation());
        dto.setDescription(department.getDescription());

        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());

        return dto;
    }
}