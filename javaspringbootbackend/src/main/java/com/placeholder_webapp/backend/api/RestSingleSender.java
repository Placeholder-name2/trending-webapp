package com.placeholder_webapp.backend.api;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestSingleSender {

  private final HttpClient httpClient;

  public RestSingleSender() {
    httpClient = HttpClient.newHttpClient();
  }

  HttpResponse<String> sendRequest(HttpRequest request) {
    try {
      return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("Uncaught exception while sending request, this should not happen!");
  }
}
