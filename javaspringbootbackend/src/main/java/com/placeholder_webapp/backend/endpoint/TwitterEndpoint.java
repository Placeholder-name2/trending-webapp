package com.placeholder_webapp.backend.endpoint;

import com.placeholder_webapp.backend.api.TwitterApi;
import com.placeholder_webapp.backend.api.TwitterApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
public class TwitterEndpoint {

  private TwitterApi twitterApi;

  public TwitterEndpoint(TwitterApi twitterApi) {
    this.twitterApi = twitterApi;
  }

  @RequestMapping("/twitter")
  public Mono<List<TwitterApiResponse>> getTwitterId() {
    log.info("Testing testing...");
    Mono<List<TwitterApiResponse>> twitterTrendingCard = twitterApi.getTwitterTrendingCard();
    return twitterTrendingCard;
  }
}
