package com.placeholder_webapp.backend.api.spotify;

import com.placeholder_webapp.backend.api.adapter.internal.SpotifyTrendingResponseDto;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.common.Country;
import com.placeholder_webapp.backend.api.common.Service;

import java.time.LocalDateTime;

public class SpotifyApiResponse extends TrendingResponse {
  private String url;
  private String playlistName;

  SpotifyApiResponse(String id, String itemId, Service service, LocalDateTime datePublished, Country country, String url, String playlistName) {
    super(id, itemId, service, datePublished, country);
    this.url = url;
    this.playlistName = playlistName;
  }

  @Override
  public SpotifyTrendingResponseDto toServiceSpecificDto() {
    return new SpotifyTrendingResponseDto(url, playlistName);
  }
}
