package com.sergio.nasa_api.app.integrations.nasa.dtos.apod;


import io.swagger.v3.oas.annotations.media.Schema;

public record ApodResponse(
        @Schema(description = "Image Author", example = "Sergio Mesa")
        String copyright,
        @Schema(description = "Date associated with the image.", example = "2024-11-01")
        String date,
        @Schema(description = "Image title.", example = "Astronomy Picture of the Day")
        String title,
        @Schema(description = "Image url.", example = "https://apod.nasa.gov/apod/image.jpg")
        String url,
        @Schema(description = "HD image url", example = "https://apod.nasa.gov/apod/image.jpg")
        String hdurl,
        @Schema(description = "Explanation of the image.", example = "This is an image taken by the Hubble Telescope...")
        String explanation,
        @Schema(description = "Image media type.", example = "image")
        String media_type
) {
}
