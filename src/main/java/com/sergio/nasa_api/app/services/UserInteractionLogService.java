package com.sergio.nasa_api.app.services;

import com.sergio.nasa_api.app.entity.dto.UserInteractionLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInteractionLogService {
    Page<UserInteractionLogResponse> findAll(Pageable pageable);

    Page<UserInteractionLogResponse> findByEmail(Pageable pageable, String email);

    Page<UserInteractionLogResponse> findMyInteractions(Pageable pageable, String email);
}
