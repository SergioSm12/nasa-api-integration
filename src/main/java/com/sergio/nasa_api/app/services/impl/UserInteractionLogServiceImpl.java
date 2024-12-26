package com.sergio.nasa_api.app.services.impl;

import com.sergio.nasa_api.app.entity.dto.UserInteractionLogResponse;
import com.sergio.nasa_api.app.mapper.UserInteractionLogMapper;
import com.sergio.nasa_api.app.repository.UserInteractionLogRepository;
import com.sergio.nasa_api.app.services.UserInteractionLogService;
import com.sergio.nasa_api.app.services.ValidAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractionLogServiceImpl implements UserInteractionLogService {

    private final UserInteractionLogRepository userInteractionLogRepository;
    private final ValidAuthenticationService validAuthenticationService;

    @Override
    public Page<UserInteractionLogResponse> findAll(Pageable pageable) {
        return userInteractionLogRepository.findAll(pageable)
                .map(UserInteractionLogMapper::toDto);
    }

    @Override
    public Page<UserInteractionLogResponse> findByEmail(Pageable pageable, String email) {
        return userInteractionLogRepository.findByEmail(pageable, email)
                .map(UserInteractionLogMapper::toDto);
    }

    @Override
    public Page<UserInteractionLogResponse> findMyInteractions(Pageable pageable, String email) {
        String userLoggedIn = validAuthenticationService.getUserLoggedIn().getUsername();
        if (!userLoggedIn.equals(email)) return null;
        return userInteractionLogRepository.findByEmail(pageable, email)
                .map(UserInteractionLogMapper::toDto);
    }
}
