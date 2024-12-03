package com.sergio.nasa_api.app.springdoc.integrations;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "NASA API", description = "endpoints to interact with NASA data.")
public interface NasaControllerDocs {

    @Operation(
            summary = "Obtaining the astronomical image of the day (APOD)",
            description = "This endpoint returns the astronomical image of the day along with its description and metadata."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Data retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApodResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request. The date format must be YYYY-MM-DD"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    ResponseEntity<ApodResponse> getAstronomicPictureOfTheDay(
            @Parameter(
                    description = """
                            Date for the day you want to get the astronomical image.
                            Must be in YYYYY-MM-DD format. This is optional; if not provided, the current date will be used.
                            """,
                    example = "2024-11-01"
            )
            @RequestParam(required = false) String date
    );
}
