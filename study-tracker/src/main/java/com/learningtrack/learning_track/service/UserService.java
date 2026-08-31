package com.learningtrack.learning_track.service;

import com.learningtrack.learning_track.dto.request.LoginRequest;
import com.learningtrack.learning_track.dto.request.RegisterRequest;
import com.learningtrack.learning_track.dto.response.AuthResponse;

public interface UserService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}