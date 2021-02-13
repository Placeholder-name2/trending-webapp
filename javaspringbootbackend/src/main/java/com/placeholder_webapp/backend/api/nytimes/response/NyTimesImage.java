package com.placeholder_webapp.backend.api.nytimes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesImage {
  String url;
  String format;
}
