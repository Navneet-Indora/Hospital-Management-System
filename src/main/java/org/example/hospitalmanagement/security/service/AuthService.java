package org.example.hospitalmanagement.security.service;

import org.example.hospitalmanagement.exception.DuplicateResourceException;
import org.example.hospitalmanagement.exception.ResourceNotFoundException;
import org.example.hospitalmanagement.security.dto.requestDto.LoginRequestDto;
import org.example.hospitalmanagement.security.dto.requestDto.RegisterRequestDto;
import org.example.hospitalmanagement.security.dto.responseDto.AuthResponseDto;
import org.example.hospitalmanagement.security.entity.User;
import org.example.hospitalmanagement.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //Register
    public AuthResponseDto register(RegisterRequestDto registerRequestDto){
        //Check if email already exist
        if(userRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new DuplicateResourceException("user already exists with email:"+registerRequestDto.getEmail());
        }
        //Build user
        User user= User.builder()
                .name(registerRequestDto.getName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(registerRequestDto.getRole())
                .build();
        //Save user
        userRepository.save(user);

        //Generate token
        String token=jwtService.generateToken(user);

        return AuthResponseDto.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("User registered successfully")
                .build();
    }

    //Login
    public AuthResponseDto login(LoginRequestDto loginRequestDto){
        //Find user by email
        User user=userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with email:"+loginRequestDto.getEmail()));

        //check password
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        //Generate token
        String token= jwtService.generateToken(user);

        return AuthResponseDto.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Login successful")
                .build();
    }
}
