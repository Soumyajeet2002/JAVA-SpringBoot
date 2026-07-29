package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDocumentRequestDto;
import com.example.Employee.dto.EmployeeDocumentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {

    EmployeeDocumentResponseDto uploadDocument(
            UUID employeeId,
            EmployeeDocumentRequestDto request,
            MultipartFile file
    ) throws IOException;

    List<EmployeeDocumentResponseDto> uploadDocuments(
            UUID employeeId,
            EmployeeDocumentRequestDto request,
            MultipartFile[] files
    ) throws IOException;


    List<EmployeeDocumentResponseDto> getAllDocuments();

    List<EmployeeDocumentResponseDto> getDocumentsByEmployee(UUID employeeId);
    
}