package com.sergio.nasa_api.app.exception;

public record ApiError(
        String message,
        String backMessage,
        String method,
        String url
) {
}
