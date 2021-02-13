package com.placeholder_webapp.backend.api.nytimes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeholder_webapp.backend.api.RestSingleSender;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingApi;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.common.Country;
import com.placeholder_webapp.backend.api.nytimes.response.*;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class NyTimesApi implements TrendingApi {
  private static final String HOST = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=";
  private static final String API_KEY = "K9XBB1A3SRSGzShmIPkz2zsHL0ldsCyn";

  private RestSingleSender restSingleSender;
  private ObjectMapper objectMapper;

  public NyTimesApi(RestSingleSender restSingleSender, ObjectMapper objectMapper) {
    this.restSingleSender = restSingleSender;
    this.objectMapper = objectMapper;
  }


  @Override
  public List<TrendingResponse> getTrending() {
    HttpRequest request = HttpRequest.newBuilder(URI.create(HOST + API_KEY))
      .build();

    HttpResponse<String> response = restSingleSender.sendRequest(request);

    try {
      NyTimesTrendsResponse nyTimesTrendsResponse = objectMapper.readValue(response.body(), NyTimesTrendsResponse.class);
      return nyTimesTrendsResponse.getResults().stream()
        .map(this::parseResult)
        .collect(Collectors.toList());

    } catch (JsonProcessingException e) {
      log.warn("oh damn! NyTimes acting up: {}", e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  private NyTimesTrendingResponse parseResult(NyTimesResults nyTimesResults) {
    String title = nyTimesResults.getTitle();
    String summary = nyTimesResults.getSummary();
    String url = nyTimesResults.getUrl();
    List<NyTimesMedia> nyTimesMedia = nyTimesResults.getMedia();
    if (nyTimesMedia.size() > 0 && nyTimesMedia.get(0) != null) {
      NyTimesMedia nyTimesMediaObject = nyTimesMedia.get(0);
      String caption = nyTimesMediaObject.getCaption();
      String imageUrl = findCorrectImageUrl(nyTimesMediaObject);
      return new NyTimesTrendingResponse(summary, url, imageUrl, caption, Country.GLOBAL);
    }
    return new NyTimesTrendingResponse(summary, url, "", "", Country.GLOBAL);
  }

  private String findCorrectImageUrl(NyTimesMedia nyTimesMedia) {
    Optional<NyTimesImage> imageUrlOptional = nyTimesMedia.getMediaMetadata().stream()
      .filter(nyTimesImage -> nyTimesImage.getFormat().equals("mediumThreeByTwo440"))
      .findFirst();

    return imageUrlOptional.map(NyTimesImage::getUrl).orElse("");
  }
}
