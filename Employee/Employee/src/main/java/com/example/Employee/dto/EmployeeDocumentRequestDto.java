package com.example.Employee.dto;

import com.example.Employee.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "EmployeeDocumentRequest",
        description = "Employee document upload request"
)
public class EmployeeDocumentRequestDto {

    @NotNull(message = "Document type is required")
    @Schema(
            description = "Type of employee document",
            example = "AADHAAR",
            implementation = DocumentType.class,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private DocumentType documentType;

    @Schema(
            description = "Optional remarks about the document",
            example = "Front side of Aadhaar card"
    )
    private String remarks;
}