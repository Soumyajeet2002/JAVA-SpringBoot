package com.example.Employee.service.impl;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;
import com.example.Employee.entities.Department;
import com.example.Employee.mapper.DepartmentMapper;
import com.example.Employee.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDepartment_success() {

        DepartmentRequestDto request = new DepartmentRequestDto();

        Department department = new Department();

        Department savedDepartment = new Department();

        DepartmentResponseDto response = new DepartmentResponseDto();


        when(departmentMapper.toEntity(request))
                .thenReturn(department);

        when(departmentRepository.save(department))
                .thenReturn(savedDepartment);

        when(departmentMapper.toResponseDto(savedDepartment))
                .thenReturn(response);


        DepartmentResponseDto result =
                departmentService.createDepartment(request);


        assertNotNull(result);

        verify(departmentMapper).toEntity(request);

        verify(departmentRepository).save(department);

        verify(departmentMapper).toResponseDto(savedDepartment);
    }

    @Test
    void getAllDepartments_success() {

        Department department = new Department();

        DepartmentResponseDto response = new DepartmentResponseDto();

        when(departmentRepository.findAll())
                .thenReturn(List.of(department));

        when(departmentMapper.toResponseDto(department))
                .thenReturn(response);


        List<DepartmentResponseDto> result =
                departmentService.getAllDepartments();


        assertEquals(1, result.size());

        verify(departmentRepository).findAll();

        verify(departmentMapper).toResponseDto(department);
    }

    @Test
    void getDepartmentById_success() {

        UUID id = UUID.randomUUID();

        Department department = new Department();

        DepartmentResponseDto response =
                new DepartmentResponseDto();


        when(departmentRepository.findById(id))
                .thenReturn(Optional.of(department));

        when(departmentMapper.toResponseDto(department))
                .thenReturn(response);


        DepartmentResponseDto result =
                departmentService.getDepartmentById(id);


        assertNotNull(result);

        verify(departmentRepository).findById(id);

        verify(departmentMapper).toResponseDto(department);
    }

    @Test
    void getDepartmentById_notFound() {

        UUID id = UUID.randomUUID();

        when(departmentRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> departmentService.getDepartmentById(id)
        );

        verify(departmentRepository).findById(id);

        verifyNoInteractions(departmentMapper);
    }

    @Test
    void updateDepartment_success() {

        UUID id = UUID.randomUUID();

        DepartmentRequestDto request = new DepartmentRequestDto();

        request.setDepartmentName("IT");
        request.setDepartmentCode("IT001");
        request.setLocation("Bangalore");
        request.setDescription("Technology Department");


        Department department = new Department();

        Department updatedDepartment = new Department();

        DepartmentResponseDto response =
                new DepartmentResponseDto();


        when(departmentRepository.findById(id))
                .thenReturn(Optional.of(department));


        when(departmentRepository.save(department))
                .thenReturn(updatedDepartment);


        when(departmentMapper.toResponseDto(updatedDepartment))
                .thenReturn(response);


        DepartmentResponseDto result =
                departmentService.updateDepartment(id, request);


        assertNotNull(result);

        assertEquals(
                "IT",
                department.getDepartmentName()
        );

        assertEquals(
                "IT001",
                department.getDepartmentCode()
        );


        verify(departmentRepository)
                .findById(id);

        verify(departmentRepository)
                .save(department);

        verify(departmentMapper)
                .toResponseDto(updatedDepartment);
    }

    @Test
    void updateDepartment_notFound() {

        UUID id = UUID.randomUUID();

        DepartmentRequestDto request =
                new DepartmentRequestDto();


        when(departmentRepository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> departmentService.updateDepartment(id, request)
        );


        verify(departmentRepository)
                .findById(id);


        verify(departmentRepository, never())
                .save(any());

    }

    @Test
    void deleteDepartment_success() {

        UUID id = UUID.randomUUID();

        Department department = new Department();


        when(departmentRepository.findById(id))
                .thenReturn(Optional.of(department));


        departmentService.deleteDepartment(id);


        verify(departmentRepository)
                .findById(id);


        verify(departmentRepository)
                .delete(department);
    }

    @Test
    void deleteDepartment_notFound() {

        UUID id = UUID.randomUUID();


        when(departmentRepository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> departmentService.deleteDepartment(id)
        );


        verify(departmentRepository)
                .findById(id);


        verify(departmentRepository, never())
                .delete(any());
    }
}