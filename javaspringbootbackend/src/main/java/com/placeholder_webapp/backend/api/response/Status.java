package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Status {
  boolean retweeted;
  @JsonProperty("id_str")
  String id;
  @JsonProperty("retweet_count")
  int retweetCount;
}
