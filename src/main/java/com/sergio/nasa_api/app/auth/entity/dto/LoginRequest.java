package com.sergio.nasa_api.app.auth.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request payload for user login")
public record LoginRequest(
        @Email
        @NotBlank
        @Schema(description = "Email address of the user", example = "john.doe@example.com")
        String email,

        @NotBlank
        @Schema(description = "Password for the user account", example = "password123")
        String password
) {
}
