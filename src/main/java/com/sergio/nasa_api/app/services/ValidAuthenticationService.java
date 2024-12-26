package com.sergio.nasa_api.app.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface ValidAuthenticationService {
    UserDetails getUserLoggedIn();
}
