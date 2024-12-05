package com.sergio.nasa_api.app.springdoc.integrations;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Operation(
            summary = "Retrieve photos from Mars rovers",
            description = """
            This endpoint retrieves photos taken by the Mars rovers. 
            You can specify the rover (spirit, curiosity, opportunity) , Earth date, camera, and page number for filtering the results.
           
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Photos retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MarsRoverPhotoResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request. Ensure that the parameters are valid."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error."
            )
    })
    ResponseEntity<List<MarsRoverPhotoResponse>> getMarsRoverPhotos(
            @Parameter(
                    description = """
                        Contains filtering parameters for retrieving Mars rover photos.
                        Includes the rover name, Earth date, camera name, and page number.
                        """,
                    required = true
            )
            @Valid MarsRoverPhotoRequest request
    );
}
