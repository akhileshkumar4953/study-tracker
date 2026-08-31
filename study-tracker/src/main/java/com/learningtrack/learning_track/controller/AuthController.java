package com.learningtrack.learning_track.controller;

import org.springframework.web.bind.annotation.*;

import com.learningtrack.learning_track.dto.request.RegisterRequest;
import com.learningtrack.learning_track.service.UserService;
import com.learningtrack.learning_track.dto.request.LoginRequest;
import com.learningtrack.learning_track.dto.response.AuthResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        return service.register(request);

    }
    
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        return service.login(request);

    }

}