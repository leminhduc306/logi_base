package com.project.logibase.logibase.controller;

import com.project.logibase.logibase.dto.request.AuthRequest;
import com.project.logibase.logibase.dto.request.IntrospectRequest;
import com.project.logibase.logibase.dto.request.RegisterRequest;
import com.project.logibase.logibase.dto.response.ApiResponse;
import com.project.logibase.logibase.dto.response.AuthResponse;
import com.project.logibase.logibase.dto.response.IntrospectResponse;
import com.project.logibase.logibase.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {
        String response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<String>builder()
                        .code(201)
                        .message("User registered successfully")
                        .data(null)
                        .build()
        );
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        AuthResponse response =  authService.authenticate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<AuthResponse>builder()
                        .code(201)
                        .message("Login successful")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("auth/introspect")
    public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest introspectRequest) {
        IntrospectResponse response =  authService.introspect(introspectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<IntrospectResponse>builder()
                        .code(200)
                        .message("")
                        .data(response)
                        .build()
        );
    }




}
