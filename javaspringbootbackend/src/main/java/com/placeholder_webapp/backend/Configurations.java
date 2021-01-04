package com.placeholder_webapp.backend;

import com.placeholder_webapp.backend.api.TwitterApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class Configurations {

  @Bean
  TwitterApi twitterApi() {
    log.info("initiated: Twitter");
    return new TwitterApi();
  }
}
