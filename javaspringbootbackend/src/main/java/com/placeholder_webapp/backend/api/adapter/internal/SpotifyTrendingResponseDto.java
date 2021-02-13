package com.placeholder_webapp.backend.api.adapter.internal;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponseDatabaseDto;

public class SpotifyTrendingResponseDto implements TrendingResponseDatabaseDto {

  private String url;
  private String playlistName;

  public SpotifyTrendingResponseDto(String url, String playlistName) {
    this.url = url;
    this.playlistName = playlistName;
  }

  @Override
  public Item toDto() {
    return new Item()
      .withString("url", url)
      .withString("playlist_name", playlistName);
  }
}
