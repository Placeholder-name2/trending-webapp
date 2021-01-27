package com.placeholder_webapp.backend.api.twitter.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
  String id;
  int retweetCount;

  @JsonCreator
  public Status(@JsonProperty("id_str") String id, @JsonProperty("retweet_count") int retweetCount) {
    this.id = id;
    this.retweetCount = retweetCount;
  }
}
