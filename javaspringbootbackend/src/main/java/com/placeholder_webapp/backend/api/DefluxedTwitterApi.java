package com.placeholder_webapp.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.response.TwitterTrendingResponse;
import com.placeholder_webapp.backend.api.response.TwitterTrendsResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class DefluxedTwitterApi {
  private RestSingleSender restSingleSender;
  private ObjectMapper objectMapper;

  private static String HOST = "https://cors-anywhere.herokuapp.com/https://api.twitter.com/";
  private static String TWITTER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D";

  public DefluxedTwitterApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }


  public List<TwitterTrendingResponse> getTrending() throws JsonProcessingException {
    String trendingPath = "1.1/trends/place.json?id=2459115";
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + trendingPath))
      .GET()
      .header("Authorization", "Bearer " + TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .build();
    HttpResponse<String> response = restSingleSender.sendRequest(request);
    String substring = response.body().substring(1, response.body().length() - 1);
    TwitterTrendsResponse twitterTrendsResponse = objectMapper.readValue(substring, TwitterTrendsResponse.class);

    return getSearchResult(twitterTrendsResponse);
  }

  private List<TwitterTrendingResponse> getSearchResult(TwitterTrendsResponse twitterTrendsResponse) {
    return null;
  }
}
