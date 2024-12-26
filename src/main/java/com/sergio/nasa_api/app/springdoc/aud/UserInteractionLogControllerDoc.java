package com.sergio.nasa_api.app.springdoc.aud;

import com.sergio.nasa_api.app.entity.dto.UserInteractionLogResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "User Interaction Logs", description = "Endpoints to manage and retrieve user interaction logs.")
public interface UserInteractionLogControllerDoc {

    @Operation(
            summary = "Retrieve paginated user interaction logs",
            description = """
                    This endpoint retrieves a paginated list of user interaction logs.
                    Only users with the ADMIN role have access to this endpoint.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logs retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserInteractionLogResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination parameters. Offset must be >= 0 and limit must be > 0."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Only administrators can access this resource."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error."
            )
    })
    ResponseEntity<Page<UserInteractionLogResponse>> findAll(
            @Parameter(
                    description = "The offset (starting position) for pagination. Must be >= 0.",
                    example = "0",
                    required = false
            )
            @RequestParam(defaultValue = "0") int offset,
            @Parameter(
                    description = "The maximum number of records to retrieve. Must be > 0.",
                    example = "10",
                    required = false
            )
            @RequestParam(defaultValue = "10") int limit
    );

    @Operation(
            summary = "Retrieve paginated user interaction logs by email",
            description = """
            This endpoint retrieves a paginated list of user interaction logs for a specific user email.
            Only users with the ADMIN role have access to this endpoint.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logs retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserInteractionLogResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination parameters. Offset must be >= 0 and limit must be > 0."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Only administrators can access this resource."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No logs found for the specified email."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error."
            )
    })
    ResponseEntity<Page<UserInteractionLogResponse>> findByEmail(
            @Parameter(
                    description = "The email address to filter the logs",
                    example = "user@example.com",
                    required = true
            )
            @PathVariable String email,
            @Parameter(
                    description = "The offset (starting position) for pagination. Must be >= 0.",
                    example = "0",
                    required = false
            )
            @RequestParam(defaultValue = "0") int offset,
            @Parameter(
                    description = "The maximum number of records to retrieve. Must be > 0.",
                    example = "10",
                    required = false
            )
            @RequestParam(defaultValue = "10") int limit
    );

    @Operation(
            summary = "Retrieve user's own interaction logs",
            description = """
            This endpoint retrieves a paginated list of interaction logs for the authenticated user.
            Users can only access their own logs. Administrators can access any user's logs.
            Access is denied if the requested email doesn't match the authenticated user's email (except for admins).
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logs retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserInteractionLogResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination parameters. Offset must be >= 0 and limit must be > 0."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. The requested email doesn't match the authenticated user's email."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Authentication required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No logs found for the specified email."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error."
            )
    })
    ResponseEntity<?> findMyInteractions(
            @Parameter(
                    description = "The email address to retrieve the logs. Must match the authenticated user's email (unless admin).",
                    example = "user@example.com",
                    required = true
            )
            @PathVariable String email,
            @Parameter(
                    description = "The offset (starting position) for pagination. Must be >= 0.",
                    example = "0",
                    required = false
            )
            @RequestParam(defaultValue = "0") int offset,
            @Parameter(
                    description = "The maximum number of records to retrieve. Must be > 0.",
                    example = "10",
                    required = false
            )
            @RequestParam(defaultValue = "10") int limit
    );
}

