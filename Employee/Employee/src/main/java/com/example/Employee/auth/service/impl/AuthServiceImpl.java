package com.example.Employee.auth.service.impl;

import com.example.Employee.auth.dto.LoginRequestDto;
import com.example.Employee.auth.dto.LoginResponseDto;
import com.example.Employee.auth.dto.RegisterRequestDto;
import com.example.Employee.auth.dto.RegisterResponseDto;
import com.example.Employee.auth.service.AuthService;
import com.example.Employee.entities.UserEntity;
import com.example.Employee.enums.Role;
import com.example.Employee.repository.UserRepository;
import com.example.Employee.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // BCrypt hashing happens here
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        UserEntity savedUser = userRepository.save(user);

        RegisterResponseDto response = new RegisterResponseDto();

        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());

        return response;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDto(
                token,
                "Bearer",
                user.getEmail(),
                user.getRole().name()
        );
    }

}