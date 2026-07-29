package com.example.Employee.dto;

import com.example.Employee.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Employee document details")
public class EmployeeDocumentResponseDto {

    //    @Schema(example = "5b651b8c-6b84-4fc5-82b4-a97fd64ecf5e")
    private UUID id;

    //    @Schema(example = "1abfd7e8-fbd5-4dc6-a0f4-321d3efb0c2a")
    private UUID employeeId;

    //    @Schema(example = "AADHAAR")
    private DocumentType documentType;

    //    @Schema(example = "aadhaar.pdf")
    private String originalFileName;

    //    @Schema(example = "d87ab62b-acde-4f2f-aed8-7a4b5b91d8d2.pdf")
    private String storedFileName;

    //    @Schema(example = "uploads/d87ab62b-acde-4f2f-aed8-7a4b5b91d8d2.pdf")
    private String filePath;

    //    @Schema(example = "application/pdf")
    private String contentType;

    //    @Schema(example = "pdf")
    private String fileExtension;

    //    @Schema(example = "253687")
    private Long fileSize;

    //    @Schema(example = "2026-07-29T11:30:45")
    private LocalDateTime uploadedAt;
}