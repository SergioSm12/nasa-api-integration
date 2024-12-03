package com.sergio.nasa_api.app.springdoc;

import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@io.swagger.v3.oas.annotations.OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "NASA consumer Api with JWT authentication.",
                description = "This API provides information consumed from NASA public services.",
                version = "1.0",
                contact = @Contact(
                        name = "Sergio Mesa",
                        email = "davidmesab7@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
