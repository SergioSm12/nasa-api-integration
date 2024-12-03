package com.sergio.nasa_api.app.integrations.nasa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NasaApiConfig {

    @Value("${nasa.api.key}")
    private String apiKey;

    public Map<String, String> getAuthenticationQueryParams(){
        Map<String,String> securityQueryParams = new HashMap<>();
        securityQueryParams.put("api_key",apiKey);
        return securityQueryParams;
    }

}
