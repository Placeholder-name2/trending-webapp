package com.placeholder_webapp.backend.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Trends {
    String name;
    @JsonProperty("query")
    String hashtagUrl;
}
