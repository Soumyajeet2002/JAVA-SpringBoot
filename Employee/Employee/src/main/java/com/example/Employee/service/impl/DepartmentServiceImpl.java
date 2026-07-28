package com.example.Employee.service.impl;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;
import com.example.Employee.entities.Department;
import com.example.Employee.mapper.DepartmentMapper;
import com.example.Employee.repository.DepartmentRepository;
import com.example.Employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        Department department = departmentMapper.toEntity(requestDto);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();
    }

    @Override
    public DepartmentResponseDto getDepartmentById(UUID id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return departmentMapper.toResponseDto(department);
    }

    @Override
    public DepartmentResponseDto updateDepartment(UUID id,
                                                  DepartmentRequestDto requestDto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setDepartmentName(requestDto.getDepartmentName());
        department.setDepartmentCode(requestDto.getDepartmentCode());
        department.setLocation(requestDto.getLocation());
        department.setDescription(requestDto.getDescription());

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponseDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(UUID id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }
}