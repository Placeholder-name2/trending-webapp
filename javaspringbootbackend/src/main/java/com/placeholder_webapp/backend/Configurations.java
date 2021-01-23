package com.placeholder_webapp.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.DefluxedTwitterApi;
import com.placeholder_webapp.backend.api.RestSingleSender;
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

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  RestSingleSender restSingleSender() {
    return new RestSingleSender();
  }

  @Bean
  DefluxedTwitterApi defluxedTwitterApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    return new DefluxedTwitterApi(restSingleSender, objectMapper);
  }
}
