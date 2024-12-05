package com.sergio.nasa_api.app.integrations.nasa.repositories;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoResponse;

import java.util.List;

public interface NasaRepository {
   ApodResponse getAstronomyPictureOfTheDay(String date);

    List<MarsRoverPhotoResponse> getMarsRoverPhoto(MarsRoverPhotoRequest request);
}
