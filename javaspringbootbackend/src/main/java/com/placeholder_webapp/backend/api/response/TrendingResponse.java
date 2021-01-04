package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendingResponse {
  List<Trend> trends;

  @JsonCreator
  public TrendingResponse(List<Trend> trends) {
    this.trends = trends;
  }
}
