package com.placeholder_webapp.backend.api;

import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.web.reactive.function.BodyExtractor;

public class ResponseBodyExtractor implements BodyExtractor {

  @Override
  public Object extract(ReactiveHttpInputMessage inputMessage, Context context) {
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder stringBuilder1 = inputMessage.getBody()
      .map(dataBuffer -> stringBuilder.append(dataBuffer.read()))
      .blockLast();
    return stringBuilder1.toString();
  }
}
