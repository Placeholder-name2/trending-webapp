package com.placeholder_webapp.backend.api.nytimes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesResults {
  String url;
  String title;
  @JsonProperty("abstract")
  String summary;
  List<NyTimesMedia> media;
}
