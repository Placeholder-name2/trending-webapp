package com.placeholder_webapp.backend.api.nytimes.nytimes.response;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class NyTimesTrendingResponse extends TrendingResponse {
  private String summary;
  private String url;
  private String imageUrl;
  private String imageCaption;

  public NyTimesTrendingResponse(String title, String summary, String url, String imageUrl, String imageCaption) {
    super(UUID.randomUUID().toString(), title, "NyTimes", LocalDateTime.now(), "NyTimes?");
    this.summary = summary;
    this.url = url;
    this.imageUrl = imageUrl;
    this.imageCaption = imageCaption;
  }
}
