package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeDocumentRequestDto;
import com.example.Employee.dto.EmployeeDocumentResponseDto;
import com.example.Employee.entities.EmployeeDocument;
import com.example.Employee.entities.EmployeeEntity;
import com.example.Employee.mapper.EmployeeDocumentMapper;
import com.example.Employee.repository.EmployeeDocumentRepository;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDocumentRepository employeeDocumentRepository;
    private final EmployeeDocumentMapper employeeDocumentMapper;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public EmployeeDocumentResponseDto uploadDocument(
            UUID employeeId,
            EmployeeDocumentRequestDto request,
            MultipartFile file) throws IOException {

        // 1. Find Employee
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // 2. Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 3. Generate unique filename
        String originalFileName = file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String storedFileName = UUID.randomUUID() + extension;

        // 4. Save file to disk
        Path targetPath = uploadPath.resolve(storedFileName);

        Files.copy(
                file.getInputStream(),
                targetPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        // 5. Create Entity
        EmployeeDocument document = new EmployeeDocument();

        document.setEmployee(employee);

        document.setDocumentType(request.getDocumentType());

        document.setOriginalFileName(originalFileName);

        document.setStoredFileName(storedFileName);

        document.setFilePath(targetPath.toString());

        document.setFileSize(file.getSize());

        document.setContentType(file.getContentType());

        document.setFileExtension(extension);

        // 6. Save into DB
        EmployeeDocument savedDocument =
                employeeDocumentRepository.save(document);

        // 7. Return DTO
        return employeeDocumentMapper.toResponse(savedDocument);
    }

    @Override
    public List<EmployeeDocumentResponseDto> uploadDocuments(
            UUID employeeId,
            EmployeeDocumentRequestDto request,
            MultipartFile[] files) throws IOException {

        List<EmployeeDocumentResponseDto> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            responses.add(
                    uploadDocument(employeeId, request, file)
            );
        }

        return responses;
    }

    @Override
    public List<EmployeeDocumentResponseDto> getAllDocuments() {

        return employeeDocumentRepository.findAll()
                .stream()
                .map(employeeDocumentMapper::toResponse)
                .toList();
    }


    @Override
    public List<EmployeeDocumentResponseDto> getDocumentsByEmployee(UUID employeeId) {

        return employeeDocumentRepository.findByEmployeeId(employeeId)
                .stream()
                .map(employeeDocumentMapper::toResponse)
                .toList();
    }
}