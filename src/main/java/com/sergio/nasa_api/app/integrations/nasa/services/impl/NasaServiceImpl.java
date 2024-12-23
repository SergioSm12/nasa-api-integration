package com.sergio.nasa_api.app.integrations.nasa.services.impl;

import com.sergio.nasa_api.app.integrations.nasa.dtos.apod.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.earth.EarthImageryRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoResponse;
import com.sergio.nasa_api.app.integrations.nasa.repositories.NasaRepository;
import com.sergio.nasa_api.app.integrations.nasa.services.NasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NasaServiceImpl implements NasaService{

    private final NasaRepository nasaRepository;

    @Override
    public ApodResponse getAstronomyPictureOfTheDay(String date) {
        return nasaRepository.getAstronomyPictureOfTheDay(date);
    }

    @Override
    public List<MarsRoverPhotoResponse> getMarsRoverPhotos(MarsRoverPhotoRequest request) {
        return nasaRepository.getMarsRoverPhoto(request);
    }

    @Override
    public byte[] getEarthImagery(EarthImageryRequest request) {
        return nasaRepository.getEarthImagery(request);
    }

}
