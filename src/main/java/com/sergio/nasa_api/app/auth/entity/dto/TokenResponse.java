package com.sergio.nasa_api.app.auth.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response containing access and refresh tokens")
public record TokenResponse(
        @JsonProperty("access_token")
        @Schema(description = "Access token for user authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,

        @JsonProperty("refresh_token")
        @Schema(description = "Refresh token for renewing access", example = "dGVzdC1yZWZyZXNoLXRva2Vu...")
        String refreshToken
) {
}
