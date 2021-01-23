package com.placeholder_webapp.backend.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.placeholder_webapp.backend.api.DefluxedTwitterApi;
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
  private DefluxedTwitterApi defluxedTwitterApi;

  public TwitterEndpoint(TwitterApi twitterApi, DefluxedTwitterApi defluxedTwitterApi) {
    this.twitterApi = twitterApi;
    this.defluxedTwitterApi = defluxedTwitterApi;
  }

  @RequestMapping("/twitter")
  public Mono<List<TwitterApiResponse>> getTwitterId() {
    log.info("Testing testing...");
    Mono<List<TwitterApiResponse>> twitterTrendingCard = twitterApi.getTwitterTrendingCard();
    return twitterTrendingCard;
  }

  @RequestMapping("/deflux")
  public void getTwitterDeflux() throws JsonProcessingException {
    defluxedTwitterApi.getTrending();
  }
}
