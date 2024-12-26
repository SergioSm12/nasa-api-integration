package com.sergio.nasa_api.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionWebClient {

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ApiError> handlerGeneralException(
            WebClientException clientException,
            Exception exception,
            HttpServletRequest request
    ) {
        return switch (exception) {
            case WebClientResponseException httpClientResponseException ->
                    this.handleWebClientResponseException((WebClientResponseException) clientException, request);
            case WebClientRequestException httpClientRequestException ->
                    this.handleWebClientRequestException((WebClientRequestException) clientException, request);
            case AccessDeniedException accessDeniedException ->
                    this.handleAccessDeniedException((AccessDeniedException) exception, request);
            case AuthenticationCredentialsNotFoundException authenticationCredentialsNotFoundException ->
                    this.handleAuthenticationCredentialsNotFoundException((AuthenticationCredentialsNotFoundException) exception, request);
            default -> this.handleWebGenericException(clientException, request);
        };
    }

    private ResponseEntity<ApiError> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception, HttpServletRequest request) {
        ApiError dto = new ApiError("No tienes acceso a este recurso", exception.getMessage(), request.getMethod(), request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
    }

    private ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {
        ApiError dto = new ApiError("No tienes acceso a este recurso", exception.getMessage(), request.getMethod(), request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
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
