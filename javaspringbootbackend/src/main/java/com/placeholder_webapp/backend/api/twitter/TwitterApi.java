package com.placeholder_webapp.backend.api.twitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingApi;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.twitter.response.Status;
import com.placeholder_webapp.backend.api.twitter.response.TwitterApiResponse;
import com.placeholder_webapp.backend.api.twitter.response.TwitterSearchResult;
import com.placeholder_webapp.backend.api.twitter.response.TwitterTrendsResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class TwitterApi implements TrendingApi {
  private RestSingleSender restSingleSender;
  private ObjectMapper objectMapper;

  private static String HOST = "https://cors-anywhere.herokuapp.com/https://api.twitter.com/";
  private static String TWITTER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D";

  public TwitterApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }

  @Override
  public List<TrendingResponse> getTrending() {
    String trendingPath = "1.1/trends/place.json?id=2459115";
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + trendingPath))
      .GET()
      .header("Authorization", "Bearer " + TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .build();

    HttpResponse<String> response = restSingleSender.sendRequest(request);

    // TODO: 23/01/2021 fix parsing - error caused by "[" "]" wrapping message
    String formattedResponse = response.body().substring(1, response.body().length() - 1);

    try {
      TwitterTrendsResponse twitterTrendsResponse = objectMapper.readValue(formattedResponse, TwitterTrendsResponse.class);
      return searchTrends(twitterTrendsResponse);
    } catch (JsonProcessingException e) {
      log.warn("Twitter gone goofed: {}", e.getMessage());
      return List.of(TwitterApiResponse.empty());
    }
  }

  private List<TrendingResponse> searchTrends(TwitterTrendsResponse twitterTrendsResponse) {
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
      log.warn("We fucked up (twitter): {}", e.getMessage());
      e.printStackTrace();
    }
    return null;
  }
}
