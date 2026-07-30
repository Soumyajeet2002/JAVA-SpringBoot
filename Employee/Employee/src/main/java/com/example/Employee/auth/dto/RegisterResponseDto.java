package com.example.Employee.auth.dto;

import com.example.Employee.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    private UUID id;

    private String fullName;

    private String email;

    private Role role;
}