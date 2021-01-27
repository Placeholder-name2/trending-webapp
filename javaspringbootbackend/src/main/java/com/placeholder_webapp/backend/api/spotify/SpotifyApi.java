package com.placeholder_webapp.backend.api.spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingApi;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.spotify.response.SpotifyFeaturedPlaylistResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class SpotifyApi implements TrendingApi {

  private static final String HOST = "https://api.spotify.com/v1/browse/featured-playlists";
  private static final String ACCESS_TOKEN = "BQDY_Eskf2TdtAJUkCvJ-gHDfAol9zKMfg-ioMec2sPfOrpRWqB8NvODbmzGiE33KzOgWCInxZKeNeLvbAhuTEIcrDw0kZ820Cje8zBWwTyc0Vx6nNJNzsp127UZ3NvW7z2cz4pQb477NsrpLX08qYR9HoFmXdk";

  private RestSingleSender restSingleSender;
  private ObjectMapper objectMapper;

  public SpotifyApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }

  @Override
  public List<TrendingResponse> getTrending() {
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST))
      .GET()
      .header("Authorization", "Bearer " + ACCESS_TOKEN)
      .build();

    HttpResponse<String> response = restSingleSender.sendRequest(request);

    try {
      SpotifyFeaturedPlaylistResponse playlistResponse = objectMapper.readValue(response.body(), SpotifyFeaturedPlaylistResponse.class);
      return parseResponse(playlistResponse);

    } catch (JsonProcessingException e) {
      log.warn("Here we go again... Spotify: {}", e.getMessage());
      return null;
    }
  }

  private List<TrendingResponse> parseResponse(SpotifyFeaturedPlaylistResponse playlistResponse) {
    log.info(playlistResponse.toString());
    return playlistResponse.getPlayLists().getItems().stream()
      .map(item -> {
        String url = item.getExternalUrls().getSpotify();
        String playlistName = item.getDescription();
        return new SpotifyApiResponse(UUID.randomUUID().toString(), "SpotifyTitle", url, playlistName);
      })
      .collect(Collectors.toList());
  }
}
