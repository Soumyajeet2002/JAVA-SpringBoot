package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeRequestDto;
import com.example.Employee.dto.EmployeeResponseDto;
import com.example.Employee.entities.Department;
import com.example.Employee.entities.EmployeeEntity;
import com.example.Employee.enums.EmploymentStatus;
import com.example.Employee.enums.JobTitle;
import com.example.Employee.exception.EmployeeNotFoundException;
import com.example.Employee.mapper.EmployeeMapper;
import com.example.Employee.repository.DepartmentRepository;
import com.example.Employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getEmployeeById_success() {

        // Arrange
        UUID id = UUID.randomUUID();

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setId(id);


        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(employee));


        when(employeeMapper.toResponseDto(employee))
                .thenReturn(responseDto);


        // Act
        EmployeeResponseDto result =
                employeeService.getEmployeeById(id);


        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());


        verify(employeeRepository)
                .findById(id);

        verify(employeeMapper)
                .toResponseDto(employee);
    }

    @Test
    void getEmployeeById_notFound() {

        // Arrange
        UUID id = UUID.randomUUID();

        when(employeeRepository.findById(id))
                .thenReturn(Optional.empty());


        // Act + Assert
        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(id)
        );


        verify(employeeRepository)
                .findById(id);

        verifyNoInteractions(employeeMapper);
    }

    @Test
    void getEmployeeByEmail_success() {

        // Arrange
        String email = "john@gmail.com";

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmail(email);

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setEmail(email);


        when(employeeRepository.findByEmail(email))
                .thenReturn(Optional.of(employee));

        when(employeeMapper.toResponseDto(employee))
                .thenReturn(responseDto);


        // Act
        EmployeeResponseDto result =
                employeeService.getEmployeeByEmail(email);


        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());


        verify(employeeRepository)
                .findByEmail(email);

        verify(employeeMapper)
                .toResponseDto(employee);
    }

    @Test
    void getEmployeeByEmail_notFound() {

        String email = "unknown@gmail.com";


        when(employeeRepository.findByEmail(email))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> employeeService.getEmployeeByEmail(email)
        );


        verify(employeeRepository)
                .findByEmail(email);

        verifyNoInteractions(employeeMapper);
    }

    @Test
    void getEmployeeByCode_success() {

        // Arrange
        String employeeCode = "EMP001";

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeCode(employeeCode);

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setEmployeeCode(employeeCode);


        when(employeeRepository.findByEmployeeCode(employeeCode))
                .thenReturn(Optional.of(employee));

        when(employeeMapper.toResponseDto(employee))
                .thenReturn(responseDto);


        // Act
        EmployeeResponseDto result =
                employeeService.getEmployeeByCode(employeeCode);


        // Assert
        assertNotNull(result);
        assertEquals(employeeCode, result.getEmployeeCode());


        verify(employeeRepository)
                .findByEmployeeCode(employeeCode);

        verify(employeeMapper)
                .toResponseDto(employee);
    }

    @Test
    void getEmployeeByCode_notFound() {

        String employeeCode = "EMP999";


        when(employeeRepository.findByEmployeeCode(employeeCode))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> employeeService.getEmployeeByCode(employeeCode)
        );


        verify(employeeRepository)
                .findByEmployeeCode(employeeCode);

        verifyNoInteractions(employeeMapper);
    }

    @Test
    void getAllEmployees_success() {

        // Arrange
        int page = 0;
        int size = 10;


        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeCode("EMP001");


        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setEmployeeCode("EMP001");


        Page<EmployeeEntity> employeePage =
                new PageImpl<>(List.of(employee));


        when(employeeRepository.findAll(PageRequest.of(page, size)))
                .thenReturn(employeePage);


        when(employeeMapper.toResponseDto(employee))
                .thenReturn(responseDto);


        // Act
        Page<EmployeeResponseDto> result =
                employeeService.getAllEmployees(page, size);


        // Assert
        assertNotNull(result);

        assertEquals(1, result.getTotalElements());

        assertEquals(
                "EMP001",
                result.getContent()
                        .get(0)
                        .getEmployeeCode()
        );


        verify(employeeRepository)
                .findAll(PageRequest.of(page, size));

        verify(employeeMapper)
                .toResponseDto(employee);
    }

    @Test
    void saveEmployee_success() {

        // Arrange

        EmployeeRequestDto requestDto = new EmployeeRequestDto();

        UUID departmentId = UUID.randomUUID();
        requestDto.setDepartmentId(departmentId);


        EmployeeEntity employee = new EmployeeEntity();

        Department department = new Department();
        department.setId(departmentId);


        EmployeeEntity savedEmployee = new EmployeeEntity();
        savedEmployee.setId(UUID.randomUUID());


        EmployeeResponseDto responseDto =
                new EmployeeResponseDto();


        when(employeeMapper.toEntity(requestDto))
                .thenReturn(employee);


        when(departmentRepository.findById(departmentId))
                .thenReturn(Optional.of(department));


        when(employeeRepository.save(employee))
                .thenReturn(savedEmployee);


        when(employeeMapper.toResponseDto(savedEmployee))
                .thenReturn(responseDto);


        // Act

        EmployeeResponseDto result =
                employeeService.saveEmployee(requestDto);


        // Assert

        assertNotNull(result);


        assertEquals(
                department,
                employee.getDepartment()
        );


        verify(employeeMapper)
                .toEntity(requestDto);


        verify(departmentRepository)
                .findById(departmentId);


        verify(employeeRepository)
                .save(employee);


        verify(employeeMapper)
                .toResponseDto(savedEmployee);

    }

    @Test
    void saveEmployee_departmentNotFound() {

        // Arrange

        EmployeeRequestDto requestDto = new EmployeeRequestDto();

        UUID departmentId = UUID.randomUUID();

        requestDto.setDepartmentId(departmentId);


        EmployeeEntity employee = new EmployeeEntity();


        when(employeeMapper.toEntity(requestDto))
                .thenReturn(employee);


        when(departmentRepository.findById(departmentId))
                .thenReturn(Optional.empty());


        // Act + Assert

        assertThrows(
                RuntimeException.class,
                () -> employeeService.saveEmployee(requestDto)
        );


        // Verify

        verify(employeeMapper)
                .toEntity(requestDto);


        verify(departmentRepository)
                .findById(departmentId);


        verify(employeeRepository, never())
                .save(any());


        verify(employeeMapper, never())
                .toResponseDto(any());

    }

    @Test
    void updateEmployee_success() {

        // Arrange
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setEmployeeCode("EMP001");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john@gmail.com");
        request.setPhoneNumber("9999999999");

        request.setJobTitle(JobTitle.SOFTWARE_ENGINEER);

        request.setDepartmentId(departmentId);

        request.setManagerName("Robert");

        request.setDateOfBirth(LocalDate.of(1998, 5, 10));
        request.setJoiningDate(LocalDate.of(2024, 1, 15));

        request.setEmploymentStatus(EmploymentStatus.ACTIVE);

        request.setSalary(50000.0);

        request.setAddress("MG Road");
        request.setCity("Bangalore");
        request.setState("Karnataka");
        request.setCountry("India");

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(employeeId);

        Department department = new Department();
        department.setId(departmentId);

        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(employeeId);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(departmentRepository.findById(departmentId))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        when(employeeMapper.toResponseDto(employee))
                .thenReturn(response);

        // Act
        EmployeeResponseDto result =
                employeeService.updateEmployee(employeeId, request);

        // Assert
        assertNotNull(result);

        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john@gmail.com", employee.getEmail());
        assertEquals(department, employee.getDepartment());

        verify(employeeRepository).findById(employeeId);
        verify(departmentRepository).findById(departmentId);
        verify(employeeRepository).save(employee);
        verify(employeeMapper).toResponseDto(employee);
    }

    @Test
    void updateEmployee_employeeNotFound() {

        UUID employeeId = UUID.randomUUID();

        EmployeeRequestDto request = new EmployeeRequestDto();

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> employeeService.updateEmployee(employeeId, request)
        );

        verify(employeeRepository)
                .findById(employeeId);

        verifyNoInteractions(departmentRepository);

        verify(employeeRepository, never())
                .save(any());

        verify(employeeMapper, never())
                .toResponseDto(any());
    }

    @Test
    void updateEmployee_departmentNotFound() {

        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setDepartmentId(departmentId);

        EmployeeEntity employee = new EmployeeEntity();

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(departmentRepository.findById(departmentId))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> employeeService.updateEmployee(employeeId, request)
        );

        verify(employeeRepository)
                .findById(employeeId);

        verify(departmentRepository)
                .findById(departmentId);

        verify(employeeRepository, never())
                .save(any());

        verify(employeeMapper, never())
                .toResponseDto(any());
    }

    @Test
    void deleteEmployee_success() {

        UUID employeeId = UUID.randomUUID();

        EmployeeEntity employee = new EmployeeEntity();

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository)
                .findById(employeeId);

        verify(employeeRepository)
                .delete(employee);
    }

    @Test
    void deleteEmployee_employeeNotFound() {

        UUID employeeId = UUID.randomUUID();

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> employeeService.deleteEmployee(employeeId)
        );

        verify(employeeRepository)
                .findById(employeeId);

        verify(employeeRepository, never())
                .delete(any());
    }
}