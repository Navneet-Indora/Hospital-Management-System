package org.example.hospitalmanagement.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.hospitalmanagement.security.dto.requestDto.LoginRequestDto;
import org.example.hospitalmanagement.security.dto.requestDto.RegisterRequestDto;
import org.example.hospitalmanagement.security.dto.responseDto.AuthResponseDto;
import org.example.hospitalmanagement.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",
        description = "APIs for user registration and login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //  Register
    // POST /api/auth/register
    @Operation(summary = "Register new user",
            description = "Register as ADMIN, DOCTOR or PATIENT")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @Valid @RequestBody RegisterRequestDto registerRequestDto) {

        AuthResponseDto response = authService.register(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  Login
    // POST /api/auth/login
    @Operation(summary = "Login user",
            description = "Login with email and password to get JWT token")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {

        AuthResponseDto response = authService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
