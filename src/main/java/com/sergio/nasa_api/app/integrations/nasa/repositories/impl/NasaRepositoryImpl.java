package com.sergio.nasa_api.app.integrations.nasa.repositories.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.sergio.nasa_api.app.integrations.nasa.config.NasaApiConfig;
import com.sergio.nasa_api.app.integrations.nasa.dtos.apod.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.dtos.earth.EarthImageryRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoRequest;
import com.sergio.nasa_api.app.integrations.nasa.dtos.mrp.MarsRoverPhotoResponse;
import com.sergio.nasa_api.app.integrations.nasa.mappers.MarsRoverPhotoMapper;
import com.sergio.nasa_api.app.integrations.nasa.repositories.NasaRepository;
import com.sergio.nasa_api.app.integrations.genericservices.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class NasaRepositoryImpl implements NasaRepository {

    private static final String APOD_ENDPOINT = "/planetary/apod";
    private static final String MRP_ENDPOINT = "/mars-photos/api/v1/rovers/{rover}/photos";
    private static final String EARTH_IMAGERY = "/planetary/earth/imagery";
    private final HttpClientService httpClientService;
    private final NasaApiConfig nasaApiConfig;

    @Override
    public ApodResponse getAstronomyPictureOfTheDay(String date) {
        Map<String, String> nasaQueryParams = nasaApiConfig.getAuthenticationQueryParams();
        if (StringUtils.hasText(date)) nasaQueryParams.put("date", date);
        return httpClientService.doGet(APOD_ENDPOINT, nasaQueryParams, ApodResponse.class);
    }

    @Override
    public List<MarsRoverPhotoResponse> getMarsRoverPhoto(MarsRoverPhotoRequest request) {
        Map<String, String> nasaQueryParams = nasaApiConfig.getAuthenticationQueryParams();
        nasaQueryParams.put("earth_date", request.earth_date());
        if (StringUtils.hasText(request.camera())) nasaQueryParams.put("camera", request.camera());
        nasaQueryParams.put("page", Integer.toString(request.page()));
        String endPoint = MRP_ENDPOINT.replace("{rover}", request.rover());
        JsonNode response = httpClientService.doGet(endPoint, nasaQueryParams, JsonNode.class);
        return MarsRoverPhotoMapper.toDtoList(response);
    }

    @Override
    public byte[] getEarthImagery(EarthImageryRequest request) {
        Map<String, String> nasaQueryParams = nasaApiConfig.getAuthenticationQueryParams();
        nasaQueryParams.put("lat", request.lat().toString());
        nasaQueryParams.put("lon", request.lon().toString());
        if (request.dim() != null) nasaQueryParams.put("dim", request.dim().toString());
        if (StringUtils.hasText(request.date())) nasaQueryParams.put("date", request.date());
        return httpClientService.doGet(EARTH_IMAGERY, nasaQueryParams, byte[].class);
    }


}
