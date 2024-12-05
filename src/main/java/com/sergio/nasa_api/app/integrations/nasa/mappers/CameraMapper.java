package com.sergio.nasa_api.app.integrations.nasa.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.CameraDto;

public class CameraMapper {
    public static CameraDto toDto(JsonNode cameraNode) {
        if (cameraNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        return new CameraDto(
                cameraNode.get("id").asLong(),
                cameraNode.get("name").asText(),
                cameraNode.get("rover_id").asLong(),
                cameraNode.get("full_name").asText()
        );
    }
}
