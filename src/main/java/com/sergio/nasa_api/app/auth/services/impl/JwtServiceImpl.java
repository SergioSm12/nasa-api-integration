package com.sergio.nasa_api.app.auth.services.impl;

import com.sergio.nasa_api.app.auth.entity.Role;
import com.sergio.nasa_api.app.auth.entity.User;
import com.sergio.nasa_api.app.auth.services.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @Override
    public String generateToken(final User user) {
        return buildToken(user, jwtExpiration);
    }

    @Override
    public String generateRefreshToken(final User user) {
        return buildToken(user, refreshExpiration);
    }

    @Override
    public String extractUsername(final String refreshToken) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String refreshToken, User user) {
        final String username = extractUsername(refreshToken);
        return (username.equals(user.getEmail())) && !isTokenExpired(refreshToken);
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(final String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    private String buildToken(final User user, final long jwtExpiration) {
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .toList();
        Date issueAt = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(issueAt.getTime() + (jwtExpiration * 60 * 1000));

        return Jwts.builder()
                .claims(Map.of(
                        "name", user.getName(),
                        "roles", roles
                ))
                .subject(user.getEmail())
                .issuedAt(issueAt)
                .expiration(expirationDate)
                .header().add("typ", "JWT").and()
                .signWith(getSignKey())
                .compact();

    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(String.valueOf(SECRET_KEY));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
