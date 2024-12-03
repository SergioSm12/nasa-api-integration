package com.sergio.nasa_api.app.integrations.nasa.services;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import reactor.core.publisher.Mono;

public interface NasaService {
   ApodResponse getAstronomyPictureOfTheDay(String date);
}
