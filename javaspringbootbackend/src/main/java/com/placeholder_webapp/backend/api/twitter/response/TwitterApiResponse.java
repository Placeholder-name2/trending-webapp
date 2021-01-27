package com.placeholder_webapp.backend.api.twitter.response;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;

import java.time.LocalDateTime;


public class TwitterApiResponse extends TrendingResponse {
  private String trendingHashTag;
  private int retweetCount;
  private String tweetId;

  public TwitterApiResponse(String id, String title, String trendingHashtag, int retweetCount, String tweetId) {
    super(
      id,
      title,
      "Twitter",
      LocalDateTime.now(),
      "Twitter?"
    );
    trendingHashTag = trendingHashtag;
    this.retweetCount = retweetCount;
    this.tweetId = tweetId;
  }

  public static TwitterApiResponse empty() {
    return new TwitterApiResponse("", "", "", 0, "");
  }
}
