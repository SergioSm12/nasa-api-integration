package com.sergio.nasa_api.app.repository;

import com.sergio.nasa_api.app.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {
    Page<UserInteractionLog> findByEmail(Pageable pageable, String email);
}
