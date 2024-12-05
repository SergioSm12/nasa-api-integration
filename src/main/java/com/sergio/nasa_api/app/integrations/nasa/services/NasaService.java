package com.sergio.nasa_api.app.integrations.nasa.services;

import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.Mrp.MarsRoverPhotoResponse;

import java.util.List;

public interface NasaService {
   ApodResponse getAstronomyPictureOfTheDay(String date);

    List<MarsRoverPhotoResponse> getMarsRoverPhotos(MarsRoverPhotoRequest request);
}
