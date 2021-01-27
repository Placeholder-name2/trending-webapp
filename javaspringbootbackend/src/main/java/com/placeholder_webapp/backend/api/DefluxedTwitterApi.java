package com.placeholder_webapp.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.common.TrendingApi;
import com.placeholder_webapp.backend.api.response.Status;
import com.placeholder_webapp.backend.api.response.TwitterSearchResult;
import com.placeholder_webapp.backend.api.response.TwitterTrendsResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefluxedTwitterApi implements TrendingApi {
  private RestSingleSender restSingleSender;
  private ObjectMapper objectMapper;

  private static String HOST = "https://cors-anywhere.herokuapp.com/https://api.twitter.com/";
  private static String TWITTER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D";

  public DefluxedTwitterApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }




  public List<TwitterApiResponse> getTrending() {
    String trendingPath = "1.1/trends/place.json?id=2459115";
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + trendingPath))
      .GET()
      .header("Authorization", "Bearer " + TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .build();

    HttpResponse<String> response = restSingleSender.sendRequest(request);

    // TODO: 23/01/2021 fix parsing - error caused by "[" "]" wrapping message
    String substring = response.body().substring(1, response.body().length() - 1);

    // FIXME: 23/01/2021 This should NOT be a null
    TwitterTrendsResponse twitterTrendsResponse = null;
    try {
      twitterTrendsResponse = objectMapper.readValue(substring, TwitterTrendsResponse.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }


    return twitterTrendsResponse.getTrends().stream().map(trend -> {
      Status status = getSearchResult(trend.getQuery()).getStatuses().stream()
        .max(Comparator.comparingInt(Status::getRetweetCount))
        .orElseThrow();
      return new TwitterApiResponse(
        UUID.randomUUID().toString(),
        "",
        trend.getName(),
        trend.getTweetVolume(),
        status.getId()
      );
    }).collect(Collectors.toList());
  }


  private TwitterSearchResult getSearchResult(String hashtagUrlParam) {
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + "1.1/search/tweets.json?q=" + hashtagUrlParam))
      .GET()
      .header("Authorization", "Bearer " + TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .build();

    HttpResponse<String> response = restSingleSender.sendRequest(request);
    try {
      return objectMapper.readValue(response.body(), TwitterSearchResult.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
