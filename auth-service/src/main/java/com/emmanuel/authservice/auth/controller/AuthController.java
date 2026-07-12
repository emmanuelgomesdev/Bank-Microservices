package com.emmanuel.authservice.auth.controller;

import com.emmanuel.authservice.auth.dto.request.AuthRequest;
import com.emmanuel.authservice.auth.dto.request.LoginRequest;
import com.emmanuel.authservice.auth.dto.response.AuthResponse;
import com.emmanuel.authservice.auth.dto.response.LoginResponse;
import com.emmanuel.authservice.auth.mapper.AuthRestMapper;
import com.emmanuel.authservice.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthRestMapper mapper;

    public AuthController(AuthService authService, AuthRestMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRequest request){

        var command = mapper.toCommand(request);
        var result = authService.register(command);
        var response = mapper.toResponse(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){

        var command = mapper.toCommand(request);
        var result = authService.login(command);
        var response =  mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }
}
