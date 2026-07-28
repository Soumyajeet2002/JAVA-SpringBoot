package com.example.Employee.service;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto getDepartmentById(UUID id);

    DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto requestDto);

    void deleteDepartment(UUID id);



}
