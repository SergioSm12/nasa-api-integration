package com.sergio.nasa_api.app.interceptors;

import com.sergio.nasa_api.app.entity.UserInteractionLog;
import com.sergio.nasa_api.app.exception.ApiErrorException;
import com.sergio.nasa_api.app.repository.UserInteractionLogRepository;
import com.sergio.nasa_api.app.services.ValidAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserInteractionLogInterceptor implements HandlerInterceptor {

    private final UserInteractionLogRepository userInteractionLogRepository;
    private final ValidAuthenticationService validAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/nasa")) {
            UserInteractionLog userLog = new UserInteractionLog();
            userLog.setHttpMethod(request.getMethod());
            userLog.setUrl(request.getRequestURL().toString());
            UserDetails user = validAuthenticationService.getUserLoggedIn();
            userLog.setEmail(user.getUsername());
            userLog.setRemoteAddress(request.getRemoteAddr());
            userLog.setTimestamp(LocalDateTime.now());
            try {
                userInteractionLogRepository.save(userLog);
                return true;
            } catch (Exception ex) {
                throw new ApiErrorException("No se logro guardar el registro de la interacción correctamente");
            }
        }

        return true;
    }
}
