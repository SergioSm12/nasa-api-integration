package com.sergio.nasa_api.app.integrations.nasa.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.CameraDto;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.RoverDto;

import java.util.List;
import java.util.stream.StreamSupport;

public class MarsRoverPhotoMapper {

    public static List<MarsRoverPhotoResponse> toDtoList(JsonNode rootNode) {
        ArrayNode resultNode = getResultsNode(rootNode);
        return StreamSupport.stream(resultNode.spliterator(), false)
                .map(MarsRoverPhotoMapper::toDto)
                .toList();
    }

    private static MarsRoverPhotoResponse toDto(JsonNode photoNode) {
        if (photoNode == null || !photoNode.hasNonNull("id") || !photoNode.hasNonNull("earth_date")) {
            throw new IllegalArgumentException("El nodo JSON del Photo no contiene información válida");
        }

        JsonNode cameraNode = photoNode.get("camera");
        CameraDto cameraDto = cameraNode != null ? CameraMapper.toDto(cameraNode) : null;

        JsonNode roverNode = photoNode.get("rover");
        RoverDto roverDto = roverNode != null ? RoverMapper.toDto(roverNode) : null;

        return new MarsRoverPhotoResponse(
                Long.parseLong(photoNode.get("id").asText()),
                photoNode.get("sol").asText(),
                cameraDto,
                photoNode.get("img_src").asText(),
                photoNode.get("earth_date").asText(),
                roverDto
        );
    }

    private static ArrayNode getResultsNode(JsonNode rootNode) {
        if (rootNode == null || !rootNode.hasNonNull("photos")) {
            throw new IllegalArgumentException("El nodo JSON no contiene el campo 'photos'");
        }
        return (ArrayNode) rootNode.get("photos");
    }
}
