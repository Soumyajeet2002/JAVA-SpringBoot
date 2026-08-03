package com.example.Employee.service.impl;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;
import com.example.Employee.entities.Department;
import com.example.Employee.mapper.DepartmentMapper;
import com.example.Employee.repository.DepartmentRepository;
import com.example.Employee.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
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
    @CacheEvict(value = "departments", allEntries = true)
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        log.info("Creating department. Clearing departments cache.");

        Department department = departmentMapper.toEntity(requestDto);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    @Cacheable("departments")
    public List<DepartmentResponseDto> getAllDepartments() {

        log.info("Fetching departments from DATABASE...");

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();
    }

    @Override
    @Cacheable(value = "department", key = "#id")
    public DepartmentResponseDto getDepartmentById(UUID id) {

        log.info("Fetching department {} from DATABASE...", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return departmentMapper.toResponseDto(department);
    }

    @Override
    @CacheEvict(value = {"departments", "department"}, allEntries = true)
    public DepartmentResponseDto updateDepartment(UUID id,
                                                  DepartmentRequestDto requestDto) {

        log.info("Updating department {}. Clearing cache.", id);

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
    @CacheEvict(value = {"departments", "department"}, allEntries = true)
    public void deleteDepartment(UUID id) {

        log.info("Deleting department {}. Clearing cache.", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }
}