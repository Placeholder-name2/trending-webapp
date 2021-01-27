package com.placeholder_webapp.backend.api.adapter.external;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
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

  public Endpoint(TwitterApi twitterApi, SpotifyApi spotifyApi) {
    this.twitterApi = twitterApi;
    this.spotifyApi = spotifyApi;
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
}
