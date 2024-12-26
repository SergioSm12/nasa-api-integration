package com.sergio.nasa_api.app.mapper;

import com.sergio.nasa_api.app.entity.UserInteractionLog;
import com.sergio.nasa_api.app.entity.dto.UserInteractionLogResponse;

public class UserInteractionLogMapper {


    public static UserInteractionLogResponse toDto(UserInteractionLog entity) {
        if (entity == null) return null;
        return new UserInteractionLogResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getHttpMethod(),
                entity.getEmail(),
                entity.getTimestamp(),
                entity.getRemoteAddress()
        );
    }
}
