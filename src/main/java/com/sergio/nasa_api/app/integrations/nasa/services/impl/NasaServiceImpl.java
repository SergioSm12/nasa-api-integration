package com.sergio.nasa_api.app.integrations.nasa.services.impl;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.repositories.NasaRepository;
import com.sergio.nasa_api.app.integrations.nasa.services.NasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NasaServiceImpl implements NasaService{

    private final NasaRepository nasaRepository;

    @Override
    public ApodResponse getAstronomyPictureOfTheDay(String date) {
        return nasaRepository.getAstronomyPictureOfTheDay(date);
    }

}
