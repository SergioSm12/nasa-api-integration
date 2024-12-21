package com.sergio.nasa_api.app.integrations.nasa.services;

import com.sergio.nasa_api.app.integrations.nasa.dtos.apod.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.earth.EarthImageryRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoResponse;

import java.util.List;

public interface NasaService {
   ApodResponse getAstronomyPictureOfTheDay(String date);

    List<MarsRoverPhotoResponse> getMarsRoverPhotos(MarsRoverPhotoRequest request);

    byte[] getEarthImagery(EarthImageryRequest request);
}
