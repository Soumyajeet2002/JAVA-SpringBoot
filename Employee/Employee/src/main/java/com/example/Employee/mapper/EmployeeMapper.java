package com.example.Employee.mapper;

import com.example.Employee.dto.EmployeeRequestDto;
import com.example.Employee.dto.EmployeeResponseDto;
import com.example.Employee.entities.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeRequestDto dto) {

        EmployeeEntity employee = new EmployeeEntity();

        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());

        employee.setJobTitle(dto.getJobTitle());
//        employee.setDepartment(dto.getDepartment());
        employee.setManagerName(dto.getManagerName());

        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setJoiningDate(dto.getJoiningDate());

        employee.setEmploymentStatus(dto.getEmploymentStatus());
        employee.setSalary(dto.getSalary());

        employee.setAddress(dto.getAddress());
        employee.setCity(dto.getCity());
        employee.setState(dto.getState());
        employee.setCountry(dto.getCountry());

        return employee;
    }


    public EmployeeResponseDto toResponseDto(EmployeeEntity employee) {

        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());

        dto.setEmployeeCode(employee.getEmployeeCode());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());

        dto.setJobTitle(employee.getJobTitle());

        dto.setDepartmentId(employee.getDepartment().getId());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());

        dto.setManagerName(employee.getManagerName());

        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setJoiningDate(employee.getJoiningDate());

        dto.setEmploymentStatus(employee.getEmploymentStatus());
        dto.setSalary(employee.getSalary());

        dto.setAddress(employee.getAddress());
        dto.setCity(employee.getCity());
        dto.setState(employee.getState());
        dto.setCountry(employee.getCountry());

        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());

        return dto;
    }
}