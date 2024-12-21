package com.sergio.nasa_api.app.integrations.nasa.dtos.mrp;

public record CameraDto(
        Long id,
        String name,
        Long rover_id,
        String full_name
) {
}
