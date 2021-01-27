package com.placeholder_webapp.backend.api.twitter.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class TwitterTrendingResponse {
  List<Trend> trends;

  @JsonCreator
  public TwitterTrendingResponse(List<Trend> trends) {
    this.trends = trends;
  }
}
