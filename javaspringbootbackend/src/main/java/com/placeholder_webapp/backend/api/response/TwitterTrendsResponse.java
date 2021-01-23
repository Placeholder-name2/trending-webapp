package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterTrendsResponse {
  List<Trend> trends;

  @JsonCreator
  public TwitterTrendsResponse(@JsonProperty("trends") List<Trend> trends) {
    this.trends = trends;
  }
}
