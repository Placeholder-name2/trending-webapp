package com.placeholder_webapp.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.twitter.TwitterApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class Configurations {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  RestSingleSender restSingleSender() {
    return new RestSingleSender();
  }

  @Bean
  TwitterApi twitterApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    return new TwitterApi(restSingleSender, objectMapper);
  }
}
