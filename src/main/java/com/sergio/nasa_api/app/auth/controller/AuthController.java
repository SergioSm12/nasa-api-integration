package com.sergio.nasa_api.app.auth.controller;

import com.sergio.nasa_api.app.auth.entity.dto.LoginRequest;
import com.sergio.nasa_api.app.auth.entity.dto.RegisterRequest;
import com.sergio.nasa_api.app.auth.entity.dto.TokenResponse;
import com.sergio.nasa_api.app.auth.services.AuthService;
import com.sergio.nasa_api.app.springdoc.auth.AuthControllerDocs;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @PostMapping("/register")
    @Override
    public ResponseEntity<?> register(@Valid @RequestBody final RegisterRequest request, BindingResult result) {
        if (result.hasFieldErrors()) return validation(result);
        final TokenResponse savedUser = authService.register(request);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> login(@Valid @RequestBody final LoginRequest request, BindingResult result) {
        if (result.hasFieldErrors()) return validation(result);
        final TokenResponse token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    @Override
    @SecurityRequirement(name = "BearerAuth")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return authService.refreshToken(authHeader);
    }


    public ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
