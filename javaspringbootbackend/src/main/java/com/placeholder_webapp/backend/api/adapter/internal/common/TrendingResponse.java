package com.placeholder_webapp.backend.api.adapter.internal.common;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.placeholder_webapp.backend.api.common.Country;
import com.placeholder_webapp.backend.api.common.Service;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class TrendingResponse {
  private String id;
  private String itemId;
  private Service service;
  private LocalDateTime datePublished;
  private Country country;

  public TrendingResponse(String id, String itemId, Service service, LocalDateTime datePublished, Country country) {
    this.id = id;
    this.itemId = itemId;
    this.service = service;
    this.datePublished = datePublished;
    this.country = country;
  }

  public Item toTrendingItemDto() {
    return new Item()
      .withString("id", id)
      .withString("item_id", itemId)
      .withString("service", service.name())
      .withString("date", datePublished.format(DateTimeFormatter.ISO_DATE));
  }

  public TrendingResponseDatabaseDto toServiceSpecificDto() {
    throw new RuntimeException("not implemented!");
  }
}
