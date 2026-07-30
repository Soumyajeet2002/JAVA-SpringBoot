package com.example.Employee.auth.service;

import com.example.Employee.auth.dto.LoginRequestDto;
import com.example.Employee.auth.dto.LoginResponseDto;
import com.example.Employee.auth.dto.RegisterRequestDto;
import com.example.Employee.auth.dto.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);

    LoginResponseDto login(LoginRequestDto request);

}