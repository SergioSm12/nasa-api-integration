package com.sergio.nasa_api.app.integrations.nasa.dtos.earth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EarthImageryRequest(
        @Schema(description = """
                Latitude of the location for which the satellite image is requested. 
                This parameter is required and must be a valid latitude value between -90 and 90.
                """,
                example = "29.78")
        @NotNull(message = "The latitude is required")
        Double lat,

        @Schema(description = """
                Longitude of the location for which the satellite image is requested. 
                This parameter is required and must be a valid longitude value between -180 and 180.
                """,
                example = "-95.33")
        @NotNull(message = "The longitude is required")
        Double lon,

        @Schema(description = """
                The width and height of the image in degrees. This is optional, and if not specified, 
                a default dimension of 0.1 degrees will be used. The value must be between 0.01 and 1.0.
                """,
                example = "0.15")
        Double dim,

        @Schema(description = """
                The date for which the satellite image is requested. This parameter is optional, and 
                if not provided, the most recent available image will be returned. The format must be YYYY-MM-dd.
                """,
                example = "2024-01-01")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "The date must be in the format YYYY-MM-dd"
        )
        String date
) {
}
