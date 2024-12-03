package com.sergio.nasa_api.app.integrations.nasa.repositories.impl;

import com.sergio.nasa_api.app.integrations.nasa.config.NasaApiConfig;
import com.sergio.nasa_api.app.integrations.nasa.dtos.ApodResponse;
import com.sergio.nasa_api.app.integrations.nasa.repositories.NasaRepository;
import com.sergio.nasa_api.app.integrations.genericservices.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.Map;

@Repository
@RequiredArgsConstructor
public class NasaRepositoryImpl implements NasaRepository {

    private final HttpClientService httpClientService;
    private final NasaApiConfig nasaApiConfig;

    @Override
    public ApodResponse getAstronomyPictureOfTheDay(String date) {
        Map<String, String> nasaQueryParams = nasaApiConfig.getAuthenticationQueryParams();
        if (StringUtils.hasText(date)) nasaQueryParams.put("date", date);
        String endPoint = "/planetary/apod";
        return httpClientService.doGet(endPoint, nasaQueryParams, ApodResponse.class);
    }


}
