package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trend {
  String name;
  String query;

  @JsonCreator
  public Trend(String name, String query) {
    this.name = name;
    this.query = query;
  }
}
