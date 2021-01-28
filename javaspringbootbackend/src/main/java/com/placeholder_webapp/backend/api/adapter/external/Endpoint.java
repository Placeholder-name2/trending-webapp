package com.placeholder_webapp.backend.api.adapter.external;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.nytimes.nytimes.NyTimesApi;
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

  public Endpoint(TwitterApi twitterApi, SpotifyApi spotifyApi, NyTimesApi nyTimesApi) {
    this.twitterApi = twitterApi;
    this.spotifyApi = spotifyApi;
    this.nyTimesApi = nyTimesApi;
  }

  @RequestMapping("/")
  public String welcome() {
    return "Hello you! try /spotify (requires an updated token atm..) or /twitter!";
  }

  @RequestMapping("/twitter")
  public List<TrendingResponse> getTwitterTrending() {
    return twitterApi.getTrending();
  }

  @RequestMapping("/spotify")
  public List<TrendingResponse> getSpotifyTrending() {
    return spotifyApi.getTrending();
  }

  @RequestMapping("/nytimes")
  public List<TrendingResponse> getNyTimesTrending() {
    return nyTimesApi.getTrending();
  }
}
