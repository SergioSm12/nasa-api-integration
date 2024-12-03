package com.sergio.nasa_api.app.integrations.nasa.repositories;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;

public interface NasaRepository {
   ApodResponse getAstronomyPictureOfTheDay(String date);
}
