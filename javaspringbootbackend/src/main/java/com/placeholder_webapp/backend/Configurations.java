package com.placeholder_webapp.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.adapter.internal.common.DynamoDbClient;
import com.placeholder_webapp.backend.api.nytimes.NyTimesApi;
import com.placeholder_webapp.backend.api.spotify.SpotifyApi;
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

  @Bean
  SpotifyApi spotifyApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    return new SpotifyApi(restSingleSender, objectMapper);
  }

  @Bean
  NyTimesApi nyTimesApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    return new NyTimesApi(restSingleSender, objectMapper);
  }

  @Bean
  DynamoDbClient dynamoDbClient() {
    return new DynamoDbClient();
  }
}
