package com.example.Employee.auth.dto;

import com.example.Employee.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String fullName;

    private String email;

    private String password;

    private Role role;
}