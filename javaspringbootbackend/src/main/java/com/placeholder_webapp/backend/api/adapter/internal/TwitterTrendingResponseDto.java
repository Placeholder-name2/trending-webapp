package com.placeholder_webapp.backend.api.adapter.internal;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponseDatabaseDto;

public class TwitterTrendingResponseDto implements TrendingResponseDatabaseDto {
  private String itemId;
  private String trendingHashTag;
  private int retweetCount;
  private String tweetId;

  public TwitterTrendingResponseDto(String itemId, String trendingHashTag, int retweetCount, String tweetId) {
    this.itemId = itemId;
    this.trendingHashTag = trendingHashTag;
    this.retweetCount = retweetCount;
    this.tweetId = tweetId;
  }

  @Override
  public Item toDto() {
    return new Item()
      .withPrimaryKey("item_id", itemId)
      .withString("trending_hashtag", trendingHashTag)
      .withInt("retweet_count", retweetCount)
      .withString("tweet_id", tweetId);
  }
}
