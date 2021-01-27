package com.placeholder_webapp.backend.api.spotify.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyFeaturedPlaylistResponse {

  private PlayList playLists;

  public SpotifyFeaturedPlaylistResponse(@JsonProperty("playlists") PlayList playLists) {
    this.playLists = playLists;
  }
}
