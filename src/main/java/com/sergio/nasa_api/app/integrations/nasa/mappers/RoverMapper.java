package com.sergio.nasa_api.app.integrations.nasa.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.RoverDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RoverMapper {
    public static RoverDto toDto(JsonNode roverNode) {
        if (roverNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }
        // Formato para parsear las fechas
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Mapear las cámaras
        List<RoverDto.CameraDtoRover> cameras = new ArrayList<>();
        if (roverNode.has("cameras") && roverNode.get("cameras").isArray()) {
            for (JsonNode cameraNode : roverNode.get("cameras")) {
                cameras.add(new RoverDto.CameraDtoRover(
                        cameraNode.get("name").asText(),
                        cameraNode.get("full_name").asText()
                ));
            }
        }

        return new RoverDto(
                roverNode.get("id").asLong(),
                roverNode.get("name").asText(),
                LocalDate.parse(roverNode.get("landing_date").asText(), dateFormatter),
                LocalDate.parse(roverNode.get("launch_date").asText(), dateFormatter),
                roverNode.get("status").asText(),
                roverNode.get("max_sol").asInt(),
                LocalDate.parse(roverNode.get("max_date").asText(), dateFormatter),
                roverNode.get("total_photos").asInt(),
                cameras
        );
    }
}
