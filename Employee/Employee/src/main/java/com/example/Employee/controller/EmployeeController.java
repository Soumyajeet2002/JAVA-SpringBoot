package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeRequestDto;
import com.example.Employee.dto.EmployeeResponseDto;
import com.example.Employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    // CREATE
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> saveEmployee(
            @RequestBody EmployeeRequestDto requestDto) {
        log.info("Controller hit" + " " + requestDto.getFirstName());
//        System.out.println("Controller hit" + " " + requestDto.getFirstName());
//        System.out.println("Controller hit" + " " + requestDto.getEmail());
//        System.out.println("Controller hit" + " " + requestDto.getLastName());

        return new ResponseEntity<>(
                employeeService.saveEmployee(requestDto),
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable UUID id,
            @RequestBody EmployeeRequestDto requestDto) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, requestDto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable UUID id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted successfully.");
    }
}