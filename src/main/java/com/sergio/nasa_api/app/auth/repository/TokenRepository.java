package com.sergio.nasa_api.app.auth.repository;

import com.sergio.nasa_api.app.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long id);

    Optional<Token> findByToken(String jwt);
}
