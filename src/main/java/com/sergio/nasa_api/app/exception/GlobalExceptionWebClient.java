package com.sergio.nasa_api.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionWebClient {

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ApiError> handlerGeneralException(
            WebClientException exception,
            HttpServletRequest request
    ) {
        return switch (exception) {
            case WebClientResponseException httpClientResponseException ->
                    this.handleWebClientResponseException((WebClientResponseException) exception, request);
            case WebClientRequestException httpClientRequestException ->
                    this.handleWebClientRequestException((WebClientRequestException) exception, request);
            default -> this.handleWebGenericException(exception, request);
        };
    }

    private ResponseEntity<ApiError> handleWebGenericException(WebClientException exception, HttpServletRequest request) {
        ApiError error = new ApiError(
                "Error inesperado en WebClient",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private ResponseEntity<ApiError> handleWebClientRequestException(WebClientRequestException exception, HttpServletRequest request) {
        ApiError error = new ApiError(
                "Error de conexión al realizar la solicitud",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    private ResponseEntity<ApiError> handleWebClientResponseException(WebClientResponseException exception, HttpServletRequest request) {
        ApiError error = new ApiError(
                "Error en la respuesta del servidor",
                exception.getResponseBodyAsString(),
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exception.getStatusCode()).body(error);

    }


}
