package com.placeholder_webapp.backend.api.adapter.external;

import com.placeholder_webapp.backend.api.adapter.internal.common.DynamoDbClient;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.nytimes.NyTimesApi;
import com.placeholder_webapp.backend.api.spotify.SpotifyApi;
import com.placeholder_webapp.backend.api.twitter.TwitterApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class Endpoint {

  private TwitterApi twitterApi;
  private SpotifyApi spotifyApi;
  private NyTimesApi nyTimesApi;
  private DynamoDbClient db;

  public Endpoint(TwitterApi twitterApi, SpotifyApi spotifyApi, NyTimesApi nyTimesApi, DynamoDbClient db) {
    this.twitterApi = twitterApi;
    this.spotifyApi = spotifyApi;
    this.nyTimesApi = nyTimesApi;
    this.db = db;
  }

  @RequestMapping("/")
  public String welcome() {
    return "Hello you! try /spotify (requires an updated token atm..) or /twitter!";
  }

  @RequestMapping("/twitter")
  public List<TrendingResponse> getTwitterTrending() {
    List<TrendingResponse> twitterTrendingResponses = twitterApi.getTrending();
    twitterTrendingResponses.forEach(trendingResponse -> db.addItem(trendingResponse));
    return twitterTrendingResponses;
  }

  @RequestMapping("/spotify")
  public List<TrendingResponse> getSpotifyTrending() {
    List<TrendingResponse> spotifyTrendingResponses = spotifyApi.getTrending();
    spotifyTrendingResponses.forEach(trendingResponse -> db.addItem(trendingResponse));
    return spotifyTrendingResponses;
  }

  @RequestMapping("/nytimes")
  public List<TrendingResponse> getNyTimesTrending() {
    List<TrendingResponse> nyTimesTrendingResponses = nyTimesApi.getTrending();
    nyTimesTrendingResponses.forEach(trendingResponse -> db.addItem(trendingResponse));
    return nyTimesTrendingResponses;
  }
}
