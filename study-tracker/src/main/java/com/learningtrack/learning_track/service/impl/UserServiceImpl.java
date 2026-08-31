package com.learningtrack.learning_track.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learningtrack.learning_track.dto.request.RegisterRequest;
import com.learningtrack.learning_track.entity.User;
import com.learningtrack.learning_track.repository.UserRepository;
import com.learningtrack.learning_track.service.UserService;


import com.learningtrack.learning_track.dto.request.LoginRequest;
import com.learningtrack.learning_track.dto.response.AuthResponse;
import com.learningtrack.learning_track.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;
    
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        if(repository.findByEmail(request.getEmail()).isPresent()) {

        	throw new RuntimeException("Email already exists");

        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        return "User Registered Successfully";

    }
    
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();

    }

}