package com.sergio.nasa_api.app.integrations.nasa.controllers;

import com.sergio.nasa_api.app.integrations.nasa.dtos.apod.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.earth.EarthImageryRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoResponse;
import com.sergio.nasa_api.app.integrations.nasa.services.NasaService;
import com.sergio.nasa_api.app.springdoc.integrations.NasaControllerDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nasa")
@RequiredArgsConstructor
public class NasaController implements NasaControllerDocs {

    private final NasaService nasaService;

    @Override
    @GetMapping("/apod")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ApodResponse> getAstronomicPictureOfTheDay(
            @RequestParam(required = false) String date
    ) {
        return ResponseEntity.ok(nasaService.getAstronomyPictureOfTheDay(date));
    }

    @Override
    @GetMapping("/mrp")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<MarsRoverPhotoResponse>> getMarsRoverPhotos(
            @Valid MarsRoverPhotoRequest request
    ) {
        return ResponseEntity.ok(nasaService.getMarsRoverPhotos(request));
    }

    @Override
    @GetMapping("/earth")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<byte[]> getEarthImagery(
            @Valid EarthImageryRequest request
    ) {
        byte[] imageData = nasaService.getEarthImagery(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return  new ResponseEntity<>(imageData,headers, HttpStatus.OK);
    }
}
