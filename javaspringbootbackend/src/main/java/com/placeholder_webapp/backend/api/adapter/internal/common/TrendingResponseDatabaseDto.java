package com.placeholder_webapp.backend.api.adapter.internal.common;

import com.amazonaws.services.dynamodbv2.document.Item;

public interface TrendingResponseDatabaseDto {
  Item toDto();
}
