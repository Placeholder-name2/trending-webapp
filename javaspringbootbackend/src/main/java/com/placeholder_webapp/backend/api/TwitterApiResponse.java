package com.placeholder_webapp.backend.api;

public class TwitterApiResponse {
    private String id;
    private String trendingHashtag;
    private int retweetCount;

    public TwitterApiResponse(String id, String trendingHashtag, int retweetCount) {
        this.id = id;
        this.trendingHashtag = trendingHashtag;
        this.retweetCount = retweetCount;
    }

    public String getId() {
        return id;
    }

    public String getTrendingHashtag() {
        return trendingHashtag;
    }

    public int getRetweetCount() {
        return retweetCount;
    }
}
