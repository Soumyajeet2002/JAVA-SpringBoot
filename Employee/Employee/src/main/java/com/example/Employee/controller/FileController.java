package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeDocumentRequestDto;
import com.example.Employee.dto.EmployeeDocumentResponseDto;
import com.example.Employee.enums.DocumentType;
import com.example.Employee.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(
            value = "/{employeeId}/documents",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<EmployeeDocumentResponseDto> uploadDocument(

            @PathVariable UUID employeeId,

            @RequestParam DocumentType documentType,

            @RequestParam(required = false) String remarks,

            @RequestPart("file") MultipartFile file) throws IOException {

        EmployeeDocumentRequestDto request = new EmployeeDocumentRequestDto();
        request.setDocumentType(documentType);
        request.setRemarks(remarks);

        return ResponseEntity.ok(
                fileService.uploadDocument(employeeId, request, file)
        );
    }

    @GetMapping("/documents")
    public ResponseEntity<List<EmployeeDocumentResponseDto>> getAllDocuments() {

        return ResponseEntity.ok(
                fileService.getAllDocuments()
        );
    }

    @GetMapping("/{employeeId}/documents")
    public ResponseEntity<List<EmployeeDocumentResponseDto>> getDocumentsByEmployee(
            @PathVariable UUID employeeId) {

        return ResponseEntity.ok(
                fileService.getDocumentsByEmployee(employeeId)
        );
    }
}