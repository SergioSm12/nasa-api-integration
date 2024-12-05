package com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a photo taken by a Mars rover along with metadata.")
public record MarsRoverPhotoResponse(
        @Schema(
                description = "Unique identifier for the photo.",
                example = "102693"
        )
        Long id,

        @Schema(
                description = "The sol (Martian solar day) on which the photo was taken.",
                example = "1000"
        )
        String sol,

        @Schema(
                description = "Details of the camera used to take the photo.",
                implementation = CameraDto.class
        )
        @JsonProperty("camera")
        CameraDto cameraDto,

        @Schema(
                description = "URL to the image taken by the rover.",
                example = "http://mars.nasa.gov/msl-raw-images/image_name.jpg"
        )
        String img_src,

        @Schema(
                description = "The Earth date on which the photo was taken.",
                example = "2024-12-01"
        )
        String earth_date,

        @Schema(
                description = "Details of the rover that captured the photo.",
                implementation = RoverDto.class
        )
        RoverDto rover
) {
}
