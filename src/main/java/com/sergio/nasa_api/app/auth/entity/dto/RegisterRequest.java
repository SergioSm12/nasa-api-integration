package com.sergio.nasa_api.app.auth.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request payload for user registration")
public record RegisterRequest(
        @NotBlank
        @Schema(description = "User's full name", example = "John Doe")
        String name,

        @NotBlank
        @Schema(description = "Password for the user account", example = "password123")
        String password,

        @Email
        @NotBlank
        @Schema(description = "Email address of the user", example = "john.doe@example.com")
        String email,

        @Schema(description = "Flag to indicate if the user has admin privileges", example = "false")
        boolean admin
) {
}
