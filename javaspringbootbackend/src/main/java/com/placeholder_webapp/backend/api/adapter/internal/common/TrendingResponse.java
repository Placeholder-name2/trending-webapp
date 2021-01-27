package com.placeholder_webapp.backend.api.adapter.internal.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TrendingResponse {
  String id;
  String title;
  String source;
  LocalDateTime datePublished;
  String serviceId;

  public TrendingResponse(String id, String title, String source, LocalDateTime datePublished, String serviceId) {
    this.id = id;
    this.title = title;
    this.source = source;
    this.datePublished = datePublished;
    this.serviceId = serviceId;
  }
}
