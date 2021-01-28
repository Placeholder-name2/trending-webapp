package com.placeholder_webapp.backend.api.nytimes.nytimes.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesTrendsResponse {
  List<NyTimesResults> results;
}
