package com.placeholder_webapp.backend.api;

import com.placeholder_webapp.backend.api.response.Status;
import com.placeholder_webapp.backend.api.response.Trend;
import com.placeholder_webapp.backend.api.response.TwitterSearchResult;
import com.placeholder_webapp.backend.api.response.TwitterTrendingResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Slf4j
public class TwitterApi {


  private SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
  private WebClient webClient;
  private static String HOST = "https://cors-anywhere.herokuapp.com/https://api.twitter.com/";
  private static String TWITTER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D";

  public TwitterApi() {
    HttpClient httpClient = new HttpClient(sslContextFactory) {
      @Override
      public Request newRequest(URI uri) {
        Request request = super.newRequest(uri);
        return enhance(request);
      }
    };
    webClient = WebClient
      .builder()
      .clientConnector(new JettyClientHttpConnector(httpClient))
      .build();
  }

  public Mono<List<TwitterApiResponse>> getTwitterTrendingCard() {
    return getTrendingHashtags()
      .map(trendingResponse -> {
        log.info("Got Twitter trending response: " + trendingResponse.toString());
        return trendingResponse.getTrends();
      })
      .map(this::collectSearchResults);
  }

  private Mono<TwitterTrendingResponse> getTrendingHashtags() {
    // TODO: 2020-12-26 implement fetching for location
    String TRENDING_PATH = "1.1/trends/place.json?id=2459115";
    return webClient.get()
      .uri(HOST + TRENDING_PATH)
      .header("Authorization", TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .header("Connection", "keep-alive")
      .retrieve()
      .bodyToMono(TwitterTrendingResponse.class)
      .doOnError(RuntimeException::new);
  }

  private List<TwitterApiResponse> collectSearchResults(List<Trend> trends) {
    return Mono.just(trends)
      .map(this::searchForHashtag)
      .block();
  }

  private List<TwitterApiResponse> searchForHashtag(List<Trend> trendList) {
    List<TwitterApiResponse> trendingSearchResultList = new ArrayList<>();
    trendList.forEach(t -> {
      String hashtag = t.getName();
      trendingSearchResultList.add(getSearchResult(t.getQuery())
        .map(searchResult -> {
          log.info("Got Twitter search result: " + searchResult);
          return parseSearchResult(searchResult.getStatuses(), hashtag);
        })
        .single()
        .block()
      );
    });
    return trendingSearchResultList;
  }

  private Mono<TwitterSearchResult> getSearchResult(String hashtagUrlParam) {
    return webClient.get()
      .uri(HOST + "1.1/search/tweets.json?q=" + hashtagUrlParam)
      .header("Authorization", TWITTER_TOKEN)
      .header("origin", "0.0.0.0")
      .header("Content-Type", "application/json")
      .header("Connection", "keep-alive")
      .retrieve()
      .bodyToMono(TwitterSearchResult.class)
      .doOnError(RuntimeException::new);
  }

  private TwitterApiResponse parseSearchResult(List<Status> statuses, String hashtag) {
    return statuses.stream()
      .map(status -> new TwitterApiResponse(status.getId(), hashtag, status.getRetweetCount()))
      .max(Comparator.comparingInt(TwitterApiResponse::getRetweetCount))
      .orElseThrow();
  }

  ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//      if (log.isDebugEnabled()) {
      StringBuilder sb = new StringBuilder("Request: \n");
      //append clientRequest method and url
      clientRequest
        .headers()
        .forEach((name, values) -> values.forEach(value -> sb.append("key: " + name + ", value: " + value + "\n")));
      log.info(sb.toString());
//      }
      return Mono.just(clientRequest);
    });
  }

  ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//      if (log.isDebugEnabled()) {
      StringBuilder sb = new StringBuilder("Response hello: \n");
      //append clientRequest method and url
      clientResponse
        .headers()
        .asHttpHeaders()
        .forEach((name, values) -> values.forEach(value -> sb.append("key: " + name + ", value: " + value + "\n")));

      log.info(sb.toString());
//      }
      return Mono.just(clientResponse);
    });
  }

  private Request enhance(Request request) {
    StringBuilder group = new StringBuilder();
    request.onRequestBegin(theRequest -> {
      // append request url and method to group
      group.append("Sending Request with {\n Method:" + theRequest.getMethod() + "\n}");
      group.append("To Url: {" + theRequest.getMethod() + "}\n");
    });
    request.onRequestHeaders(theRequest -> {
      group.append("With headers: {\n");
      for (HttpField header : theRequest.getHeaders()) {
        group.append("key: " + header.getName() + ", ");
        group.append("value: " + header.getValue() + "\n");
        // append request headers to group
      }
      group.append("}\n");
    });
    request.onRequestContent((theRequest, content) -> {
      group.append("body: {" + content + "} \n");
      // append content to group
    });
    request.onRequestSuccess(theRequest -> {
      log.info(group.toString());
      group.delete(0, group.length());
    });
    group.append("\n");
    request.onResponseBegin(theResponse -> {
      group.append("Got response: { \n");
      group.append("Status: " + theResponse.getStatus());
      // append response status to group
    });
    request.onResponseHeaders(theResponse -> {
      for (HttpField header : theResponse.getHeaders()) {
        group.append("key: " + header.getName() + ", ");
        group.append("value: " + header.getValue() + "\n");
        // append response headers to group
      }
    });
    request.onResponseContent((theResponse, content) -> {
      byte[] bytes = content.array().clone();
      String contentString = new String(bytes, StandardCharsets.UTF_8);
      group.append("body: {\n " + contentString + " \n}");
      // append content to group
    });
    request.onResponseSuccess(theResponse -> {
      log.info(group.toString());
    });
    return request;
  }
}
