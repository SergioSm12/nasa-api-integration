package com.sergio.nasa_api.app.springdoc.auth;

import com.sergio.nasa_api.app.auth.entity.dto.LoginRequest;
import com.sergio.nasa_api.app.auth.entity.dto.RegisterRequest;
import com.sergio.nasa_api.app.auth.entity.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "NASA API AUTH", description = "endpoints for authentication (register, log in, refresh).")
public interface AuthControllerDocs {
    @Operation(summary = "Register a new user", description = "Allows a new user to register by providing necessary details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"field\":\"email\",\"message\":\"must not be empty\"}"))),
    })
    ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult result
    );

    @Operation(summary = "Log in a user", description = "Authenticates a user and returns access and refresh tokens.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, tokens returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"field\":\"email\",\"message\":\"must be a valid email\"}"))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"error\":\"Invalid email or password\"}"))),
    })
    ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request,
            BindingResult result
    );

    @Operation(
            summary = "Refresh tokens",
            description = "Allows a user to refresh their access and refresh tokens by providing a valid refresh token in the Authorization header."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tokens successfully refreshed",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid or expired refresh token",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"error\":\"Invalid or expired token\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Missing Authorization header",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"error\":\"Authorization header is required\"}")
                    )
            )
    })
    TokenResponse refreshToken(
            @Parameter(
                    name = HttpHeaders.AUTHORIZATION,
                    description = "Authorization header containing the refresh token, e.g., 'Bearer <refresh_token>'.",
                    required = true,
                    schema = @Schema(type = "string", example = "Bearer eyJhbGciOiJIUzI1Ni...")
            )
            String authHeader
    );

}
