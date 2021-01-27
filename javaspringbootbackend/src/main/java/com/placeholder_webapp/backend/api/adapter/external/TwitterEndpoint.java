package com.placeholder_webapp.backend.api.adapter.external;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.twitter.TwitterApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TwitterEndpoint {

  private TwitterApi twitterApi;

  public TwitterEndpoint(TwitterApi twitterApi) {
    this.twitterApi = twitterApi;
  }

  @RequestMapping("/twitter")
  public List<TrendingResponse> getTwitterTrending() {
    return twitterApi.getTrending();
  }
}
