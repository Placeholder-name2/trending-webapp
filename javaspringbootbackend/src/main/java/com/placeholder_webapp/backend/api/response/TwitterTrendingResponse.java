package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@ToString
public class TwitterTrendingResponse {
    List<TrendingResponse> trendingResponses;
}
