package com.placeholder_webapp.backend.api.nytimes.nytimes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesMedia {
  String caption;
  @JsonProperty("media-metadata")
  List<NyTimesImage> mediaMetadata;
}
