package com.placeholder_webapp.backend.api.twitter.response;

import com.placeholder_webapp.backend.api.adapter.internal.TwitterTrendingResponseDto;
import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;
import com.placeholder_webapp.backend.api.common.Country;
import com.placeholder_webapp.backend.api.common.Service;

import java.time.LocalDateTime;


public class TwitterApiResponse extends TrendingResponse {
  private String trendingHashTag;
  private int retweetCount;
  private String tweetId;

  public TwitterApiResponse(String id, String itemId, String trendingHashtag, int retweetCount, String tweetId, Country country) {
    super(id, itemId, Service.TWITTER, LocalDateTime.now(), country);
    trendingHashTag = trendingHashtag;
    this.retweetCount = retweetCount;
    this.tweetId = tweetId;
  }

  @Override
  public TwitterTrendingResponseDto toServiceSpecificDto() {
    return new TwitterTrendingResponseDto(getItemId(), trendingHashTag, retweetCount, tweetId);
  }
}
