package com.project.auth.service;

import com.project.auth.dtos.LoginRequest;
import com.project.auth.dtos.LoginResponse;
import com.project.auth.dtos.RegisterRequest;
import com.project.auth.dtos.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
