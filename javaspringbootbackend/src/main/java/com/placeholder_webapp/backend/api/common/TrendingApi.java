package com.placeholder_webapp.backend.api.common;

import com.placeholder_webapp.backend.api.TwitterApiResponse;

import java.util.List;

public interface TrendingApi {
  List<TwitterApiResponse> getTrending();
}
