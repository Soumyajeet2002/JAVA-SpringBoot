package com.example.Employee.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for registering a new employee")
public class RegisterRequestDto {

    @Schema(
            description = "Full name of the employee",
            example = "Soumyajeet",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String fullName;

    @Schema(
            description = "Email address of the employee",
            example = "Soumyajeet@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Schema(
            description = "Password for the employee account",
            example = "Soumyajeet",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

//    private Role role;

}