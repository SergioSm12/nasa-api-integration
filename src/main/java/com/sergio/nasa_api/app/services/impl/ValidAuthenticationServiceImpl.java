package com.sergio.nasa_api.app.services.impl;

import com.sergio.nasa_api.app.services.ValidAuthenticationService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ValidAuthenticationServiceImpl implements ValidAuthenticationService {
    @Override
    public UserDetails getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof UsernamePasswordAuthenticationToken authToken)) {
            throw new AuthenticationCredentialsNotFoundException("Se requiere autenticación completa");
        }
        return (UserDetails) authToken.getPrincipal();
    }
}
