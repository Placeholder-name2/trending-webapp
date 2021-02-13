package com.placeholder_webapp.backend.api.nytimes.response;

import com.placeholder_webapp.backend.api.adapter.internal.NyTimesTrendingResponseDto;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.common.Country;
import com.placeholder_webapp.backend.api.common.Service;

import java.time.LocalDateTime;
import java.util.UUID;

public class NyTimesTrendingResponse extends TrendingResponse {
  private String summary;
  private String url;
  private String imageUrl;
  private String imageCaption;

  public NyTimesTrendingResponse(String summary, String url, String imageUrl, String imageCaption, Country country) {
    super(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Service.NY_TIMES, LocalDateTime.now(), country);
    this.summary = summary;
    this.url = url;
    this.imageUrl = imageUrl;
    this.imageCaption = imageCaption;
  }

  @Override
  public NyTimesTrendingResponseDto toServiceSpecificDto() {
    return new NyTimesTrendingResponseDto(getItemId(), summary, url, imageUrl, imageCaption);
  }
}
