package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeRequestDto;
import com.example.Employee.dto.EmployeeResponseDto;
import com.example.Employee.entities.Department;
import com.example.Employee.entities.EmployeeEntity;
import com.example.Employee.exception.EmployeeNotFoundException;
import com.example.Employee.mapper.EmployeeMapper;
import com.example.Employee.repository.DepartmentRepository;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto) {

        EmployeeEntity employee = employeeMapper.toEntity(employeeRequestDto);

        Department department = departmentRepository
                .findById(employeeRequestDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);

        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getEmployeeByEmail(String email) {

        EmployeeEntity employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));

        return employeeMapper.toResponseDto(employee);
    }


    @Override
    public EmployeeResponseDto getEmployeeByCode(String employeeCode) {

        EmployeeEntity employee = employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + employeeCode));

        return employeeMapper.toResponseDto(employee);
    }


    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDto)
                .toList();
    }

//    @Override
//    public Page<EmployeeResponseDto> getAllEmployees(int page, int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<EmployeeEntity> employees =
//                employeeRepository.findAll(pageable);
//
//        return employees.map(employeeMapper::toResponseDto);
//    }


    @Override
    public EmployeeResponseDto getEmployeeById(UUID id) {

        EmployeeEntity employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        )
                );

        return employeeMapper.toResponseDto(employee);
    }


    @Override
    public EmployeeResponseDto updateEmployee(UUID id,
                                              EmployeeRequestDto employeeRequestDto) {

        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));


        employee.setEmployeeCode(employeeRequestDto.getEmployeeCode());

        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());

        employee.setEmail(employeeRequestDto.getEmail());
        employee.setPhoneNumber(employeeRequestDto.getPhoneNumber());


        employee.setJobTitle(employeeRequestDto.getJobTitle());
//        employee.setDepartment(employeeRequestDto.getDepartment());
        Department department = departmentRepository.findById(employeeRequestDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        employee.setManagerName(employeeRequestDto.getManagerName());


        employee.setDateOfBirth(employeeRequestDto.getDateOfBirth());
        employee.setJoiningDate(employeeRequestDto.getJoiningDate());


        employee.setEmploymentStatus(employeeRequestDto.getEmploymentStatus());

        employee.setSalary(employeeRequestDto.getSalary());


        employee.setAddress(employeeRequestDto.getAddress());
        employee.setCity(employeeRequestDto.getCity());
        employee.setState(employeeRequestDto.getState());
        employee.setCountry(employeeRequestDto.getCountry());


        EmployeeEntity updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDto(updatedEmployee);
    }


    @Override
    public void deleteEmployee(UUID id) {

        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}