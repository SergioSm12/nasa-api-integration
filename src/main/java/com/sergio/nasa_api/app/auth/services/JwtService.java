package com.sergio.nasa_api.app.auth.services;

import com.sergio.nasa_api.app.auth.entity.User;

public interface JwtService {
    String generateToken(User savedUser);

    String generateRefreshToken(User savedUser);

    String extractUsername(String refreshToken);

    boolean isTokenValid(String refreshToken, User user);
}
