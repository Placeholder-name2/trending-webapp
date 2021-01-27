package com.placeholder_webapp.backend.api.spotify;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;

import java.time.LocalDateTime;

public class SpotifyApiResponse extends TrendingResponse {
  private String url;
  private String playlistName;

  public SpotifyApiResponse(String id, String title, String url, String playlistName) {
    super(id, title, "Spotify", LocalDateTime.now(), "Spotify?");
    this.url = url;
    this.playlistName = playlistName;
  }
}
