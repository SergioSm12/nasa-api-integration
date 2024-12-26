package com.sergio.nasa_api.app.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UserInteractionLogResponse(
        Long id,
        String url,
        String httpMethod,
        String email,
        @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss.SSS")
        LocalDateTime timestamp,
        String remoteAddress
) {
}
