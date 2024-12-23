package com.sergio.nasa_api.app.auth.services.impl;

import com.sergio.nasa_api.app.auth.entity.Role;
import com.sergio.nasa_api.app.auth.entity.Token;
import com.sergio.nasa_api.app.auth.entity.User;
import com.sergio.nasa_api.app.auth.entity.dto.LoginRequest;
import com.sergio.nasa_api.app.auth.entity.dto.RegisterRequest;
import com.sergio.nasa_api.app.auth.entity.dto.TokenResponse;
import com.sergio.nasa_api.app.auth.repository.RoleRepository;
import com.sergio.nasa_api.app.auth.repository.TokenRepository;
import com.sergio.nasa_api.app.auth.repository.UserRepository;
import com.sergio.nasa_api.app.auth.services.AuthService;
import com.sergio.nasa_api.app.auth.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public TokenResponse register(final RegisterRequest request) {
        Optional<Role> optionalUserRole = roleRepository.findByName(Role.RoleEnum.ROLE_USER);
        List<Role> roles = new ArrayList<>();
        optionalUserRole.ifPresent(roles::add);
        if (request.admin()) {
            Optional<Role> optionalAdminRole = roleRepository.findByName(Role.RoleEnum.ROLE_ADMIN);
            optionalAdminRole.ifPresent(roles::add);
        }
        final User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .admin(request.admin())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialExpired(false)
                .enabled(true)
                .build();

        final User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        savedUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        savedUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse refreshToken(final String authentication) {
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }

        final String refreshToken = authentication.substring(7);
        final String username = jwtService.extractUsername(refreshToken);
        if (username == null) return null;

        final User user = userRepository.findByEmailWithRoles(username).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
        if (!isTokenValid) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final String accessToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        savedUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    private void revokeAllUserTokens(final User user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }

    private void savedUserToken(final User user, String jwtToken) {
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
