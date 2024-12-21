package com.sergio.nasa_api.app.integrations.nasa.repositories;

import com.sergio.nasa_api.app.integrations.nasa.dtos.apod.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.earth.EarthImageryRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoResponse;

import java.util.List;

public interface NasaRepository {
    ApodResponse getAstronomyPictureOfTheDay(String date);

    List<MarsRoverPhotoResponse> getMarsRoverPhoto(MarsRoverPhotoRequest request);

    byte[] getEarthImagery(EarthImageryRequest request);
}
