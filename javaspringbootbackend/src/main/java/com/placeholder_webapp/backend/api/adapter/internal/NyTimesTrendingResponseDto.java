package com.placeholder_webapp.backend.api.adapter.internal;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponseDatabaseDto;

public class NyTimesTrendingResponseDto implements TrendingResponseDatabaseDto {

  private String itemId;
  private String summary;
  private String url;
  private String imageUrl;
  private String imageCaption;

  public NyTimesTrendingResponseDto(String itemId, String summary, String url, String imageUrl, String imageCaption) {
    this.itemId = itemId;
    this.summary = summary;
    this.url = url;
    this.imageUrl = imageUrl;
    this.imageCaption = imageCaption;
  }

  @Override
  public Item toDto() {
    return new Item()
      .withPrimaryKey("item_id", itemId)
      .withString("summary", summary)
      .withString("url", url)
      .withString("image_url", imageUrl)
      .withString("image_caption", imageCaption);
  }
}
