package com.placeholder_webapp.backend.api.response;

import lombok.Data;

import java.util.List;

@Data
public class TwitterTrendsResponseList {
  List<TwitterTrendsResponse> response;
}
