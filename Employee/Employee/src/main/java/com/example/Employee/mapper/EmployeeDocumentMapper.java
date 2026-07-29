package com.example.Employee.mapper;

import com.example.Employee.dto.EmployeeDocumentResponseDto;
import com.example.Employee.entities.EmployeeDocument;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDocumentMapper {

    public EmployeeDocumentResponseDto toResponse(EmployeeDocument entity) {

        EmployeeDocumentResponseDto dto = new EmployeeDocumentResponseDto();

        dto.setId(entity.getId());

        dto.setEmployeeId(entity.getEmployee().getId());

        dto.setDocumentType(entity.getDocumentType());

        dto.setOriginalFileName(entity.getOriginalFileName());

        dto.setStoredFileName(entity.getStoredFileName());

        dto.setFilePath(entity.getFilePath());

        dto.setContentType(entity.getContentType());

        dto.setFileExtension(entity.getFileExtension());

        dto.setFileSize(entity.getFileSize());

        dto.setUploadedAt(entity.getUploadedAt());

        return dto;
    }
}