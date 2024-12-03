package com.sergio.nasa_api.app.integrations.nasa.controllers;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.services.NasaService;
import com.sergio.nasa_api.app.springdoc.integrations.NasaControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nasa")
@RequiredArgsConstructor
public class NasaController implements NasaControllerDocs {

    private final NasaService nasaService;

    @Override
    @GetMapping("/apod")
    public ResponseEntity<ApodResponse> getAstronomicPictureOfTheDay(
            @RequestParam(required = false) String date
    ) {
        return ResponseEntity.ok(nasaService.getAstronomyPictureOfTheDay(date));
    }

}
