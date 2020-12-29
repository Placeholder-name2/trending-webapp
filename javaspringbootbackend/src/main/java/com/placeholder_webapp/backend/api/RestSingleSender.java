package com.placeholder_webapp.backend.api;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestSingleSender {

    RestTemplate restTemplate;

    public RestSingleSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> send(RequestEntity request) {
        return restTemplate.exchange(request.getUrl(), request.getMethod(), request, String.class);
    }
}
