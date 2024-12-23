package com.sergio.nasa_api.app.auth.services;

import com.sergio.nasa_api.app.auth.entity.dto.LoginRequest;
import com.sergio.nasa_api.app.auth.entity.dto.RegisterRequest;
import com.sergio.nasa_api.app.auth.entity.dto.TokenResponse;

public interface AuthService {
    TokenResponse register(RegisterRequest request);

    TokenResponse login(LoginRequest request);

    TokenResponse refreshToken(String authHeader);

}
