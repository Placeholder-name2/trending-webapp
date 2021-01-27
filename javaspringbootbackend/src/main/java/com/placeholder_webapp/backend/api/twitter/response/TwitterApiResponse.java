package com.placeholder_webapp.backend.api.twitter.response;

import com.placeholder_webapp.backend.api.adapter.internal.common.TrendingResponse;

import java.time.LocalDateTime;
import java.util.Map;


public class TwitterApiResponse extends TrendingResponse {

  public TwitterApiResponse(String id, String title, String trendingHashtag, int retweetCount, String tweetId) {
    super(
      id,
      title,
      "Twitter",
      LocalDateTime.now(),
      "Twitter?",
      Map.of("trending_hashtag", trendingHashtag, "retweet_count", String.valueOf(retweetCount), "id", tweetId)
    );
  }

  public static TwitterApiResponse empty() {
    return new TwitterApiResponse("", "", "", 0, "");
  }
}
