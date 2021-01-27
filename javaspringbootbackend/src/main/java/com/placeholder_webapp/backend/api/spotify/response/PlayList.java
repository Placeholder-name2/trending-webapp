package com.placeholder_webapp.backend.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayList {

  private List<Item> items;

  public PlayList(@JsonProperty("items") List<Item> items) {
    this.items = items;
  }
}
