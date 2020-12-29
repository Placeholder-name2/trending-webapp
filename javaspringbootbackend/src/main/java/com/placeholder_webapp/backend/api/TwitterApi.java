package com.placeholder_webapp.backend.api;

import com.placeholder_webapp.backend.api.response.Status;
import com.placeholder_webapp.backend.api.response.Trends;
import com.placeholder_webapp.backend.api.response.TwitterSearchResult;
import com.placeholder_webapp.backend.api.response.TwitterTrendingResponse;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class TwitterApi {

    private WebClient webClient;
    private List<TwitterApiResponse> apiResponses;
    private static String HOST = "https://cors-anywhere.herokuapp.com/https://api.twitter.com/";
    private static String TWITTER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D";

    public TwitterApi(WebClient webClient) {
        this.webClient = webClient;
        apiResponses = new ArrayList<>();
    }

    public Flux<Publisher<? extends List<TwitterApiResponse>>> getTwitterTrendingCard() {
        return getTrendingHashtags()
                .map(trendingResponse -> {
                    log.info("Got Twitter trending response: " + trendingResponse.toString());
                    return trendingResponse.getTrendingResponses().get(0).getTrends();
                })
                .map(this::collectSearchResults);
    }

    private Flux<TwitterTrendingResponse> getTrendingHashtags() {
        // TODO: 2020-12-26 implement fetching for location
        String TRENDING_PATH = "1.1/trends/place.json?id=2459115";
        return webClient.get()
                .uri(HOST + TRENDING_PATH)
                .header("Authorization", TWITTER_TOKEN)
                .exchangeToFlux(response -> {
                    log.info("Got Trending response with status: {}", response.statusCode());
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToFlux(TwitterTrendingResponse.class);
                    } else {
                        throw new RuntimeException();
                    }
                })
                .doOnError(RuntimeException::new);
    }

    private Publisher<? extends List<TwitterApiResponse>> collectSearchResults(List<Trends> trends) {
        return Flux.just(trends)
                .flatMap(this::searchForHashtag);
    }

    private Publisher<? extends List<TwitterApiResponse>> searchForHashtag(List<Trends> trendsList) {
        List<TwitterApiResponse> trendingSearchResultList = new ArrayList<>();
        trendsList.forEach(t -> {
            String hashtag = t.getName();
            trendingSearchResultList.add(getSearchResult(t.getHashtagUrl())
                    .map(searchResult -> {
                        log.info("Got Twitter search result: " + searchResult);
                        return parseSearchResult(searchResult.getStatuses(), hashtag);
                    })
                    .single()
                    .block()
            );
        });
        return Flux.just(trendingSearchResultList);
    }

    private Flux<TwitterSearchResult> getSearchResult(String hashtagUrlParam) {
        return webClient.get()
                .uri(HOST + "1.1/search/tweets.json?q=" + hashtagUrlParam)
                .header("Authorization", TWITTER_TOKEN)
                .exchangeToFlux(response -> response.bodyToFlux(TwitterSearchResult.class));
    }

    private TwitterApiResponse parseSearchResult(List<Status> statuses, String hashtag) {
        List<TwitterApiResponse> twitterItems = statuses.stream()
                .map(status -> new TwitterApiResponse(status.getId(), hashtag, status.getRetweetCount()))
                .collect(Collectors.toList());
        TwitterApiResponse mostRetweetedItem = twitterItems.get(0);
        for (int i = 1; i < twitterItems.size(); i++) {
            TwitterApiResponse twitterItem = twitterItems.get(i);
            mostRetweetedItem = mostRetweetedItem.getRetweetCount() < twitterItem.getRetweetCount() ? twitterItem : mostRetweetedItem;
        }
        return mostRetweetedItem;
    }
}
