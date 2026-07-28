package com.example.Employee.controller;

import com.example.Employee.dto.DepartmentRequestDto;
import com.example.Employee.dto.DepartmentResponseDto;
import com.example.Employee.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @RequestBody DepartmentRequestDto requestDto) {

        return new ResponseEntity<>(
                departmentService.createDepartment(requestDto),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable UUID id,
            @RequestBody DepartmentRequestDto requestDto) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, requestDto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable UUID id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully.");
    }
}