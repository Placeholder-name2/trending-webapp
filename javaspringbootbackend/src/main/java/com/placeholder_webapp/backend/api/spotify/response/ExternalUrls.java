package com.placeholder_webapp.backend.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalUrls {

  private String spotify;

  public ExternalUrls(@JsonProperty("spotify") String spotify) {
    this.spotify = spotify.replace("https://open.spotify.com/", "https://open.spotify.com/embed/");
  }
}
