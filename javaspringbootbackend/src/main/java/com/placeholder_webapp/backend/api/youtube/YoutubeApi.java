package com.placeholder_webapp.backend.api.youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingApi;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class YoutubeApi implements TrendingApi {

  private static final String HOST = "https://youtube.googleapis.com/";
  private static final String PATH = "youtube/v3/videos";
  private static final String API_KEY = "AIzaSyAcAn4WsPxhd0I78boZgThUk8OSgvgIvFM";
  private static final String REGION = "US";

  private final RestSingleSender restSingleSender;
  private final ObjectMapper objectMapper;

  public YoutubeApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }

  @Override
  public List<TrendingResponse> getTrending() {
    // TODO: 2021-01-29 don't like this handling of url-parameters
    String urlParameters = "?part=snippet%2Cstatistics%2Cplayer&chart=mostPopular&regionCode=SE&key=" + API_KEY;
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + PATH + urlParameters))
      .GET()
      .header("Accept", "application/json")
      .build();
    HttpResponse<String> response = restSingleSender.sendRequest(request);


    return null;
  }
}
