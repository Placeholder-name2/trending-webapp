package com.placeholder_webapp.backend.api.response;

import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@ToString
public class TrendingResponse {
    List<Trends> trends;
}
