package com.sergio.nasa_api.app.integrations.nasa.dtos.mrp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MarsRoverPhotoRequest(
        @Schema(description = "rover name, A rover is an unmanned space vehicle. In this case we have three possibilities spirit, opportunity and curiosity",
                example = "curiosity")
        @NotBlank(message = "The rover name is required ") String rover,
        @Schema(description = "Date of the photo taken by the rover. 'Check the dates available for each rover.'",
                example = "2020-12-19")
        @NotBlank(message = "The date (earth_date) is mandatory")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "La fecha debe tener el formato YYYY-MM-dd"
        )
        String earth_date,
        @Schema(description = "Type of camera with which the photo was taken. We have '" +
                "FHAZ, RHAZ, MAST, CHEMCAM, MAHLI, MARDI, NAVCAM, PANCAM, MINITES', This parameter is optional ",
                example = "FHAZ")
        String camera,
        @Schema(description = "Number of pages we want to display, each page has 25 items.", example = "1")
        @Min(value = 1, message = "The page must be greater than or equal to 1.")
        int page
) {
}
