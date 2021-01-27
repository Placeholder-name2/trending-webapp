package com.placeholder_webapp.backend.api.twitter.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trend {
  int tweetVolume;
  String name;
  String query;

  @JsonCreator
  public Trend(@JsonProperty("name") String name, @JsonProperty("query") String query, @JsonProperty("tweet_volume") int tweetVolume) {
    this.name = name;
    this.query = query;
    this.tweetVolume = tweetVolume;
  }
}
