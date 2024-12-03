package com.sergio.nasa_api.app.integrations.genericservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HttpClientServiceImpl implements HttpClientService {

    private final WebClient webClient;


    @Override
    public <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint);
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(responseType)
                .block(); //syncrono se puede cambiar si se requiere reactivo
    }

    @Override
    public <T, R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        return webClient.post()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint);
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .bodyValue(bodyRequest)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T, R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        return webClient.put()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint);
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .bodyValue(bodyRequest)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    @Override
    public <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.delete()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint);
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
