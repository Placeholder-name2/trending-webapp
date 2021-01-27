package com.placeholder_webapp.backend.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

  private ExternalUrls externalUrls;
  private String description;

  public Item(@JsonProperty("external_urls") ExternalUrls externalUrls, @JsonProperty("description") String description) {
    this.externalUrls = externalUrls;
    this.description = description;
  }
}
