package com.sergio.nasa_api.app.integrations.nasa.dtos.mrp;

import java.time.LocalDate;
import java.util.List;

public record RoverDto(
        Long id,
        String name,
        LocalDate landing_date,
        LocalDate launch_date,
        String status,
        int max_sol,
        LocalDate max_date,
        int total_photos,
        List<CameraDtoRover> cameras
) {
    public record CameraDtoRover(
            String name,
            String full_name
    ) {
    }
}

