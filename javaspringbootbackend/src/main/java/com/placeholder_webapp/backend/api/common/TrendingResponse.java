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
}
