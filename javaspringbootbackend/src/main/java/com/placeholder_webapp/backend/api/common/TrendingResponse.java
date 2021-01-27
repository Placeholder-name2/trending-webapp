package com.placeholder_webapp.backend.api.common;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class TrendingResponse {
  String id;
  String title;
  String source;
  LocalDateTime datePublished;
  String serviceId;
  Map<String, String> data;

  public TrendingResponse(String id, String title, String source, LocalDateTime datePublished, String serviceId, Map<String, String> data) {
    this.id = id;
    this.title = title;
    this.source = source;
    this.datePublished = datePublished;
    this.serviceId = serviceId;
    this.data = data;
  }
}
