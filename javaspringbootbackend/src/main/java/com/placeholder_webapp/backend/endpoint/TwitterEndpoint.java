package com.placeholder_webapp.backend.endpoint;

import com.placeholder_webapp.backend.api.TwitterApi;
import com.placeholder_webapp.backend.api.TwitterApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RestController
public class TwitterEndpoint {

    TwitterApi twitterApi;

    public TwitterEndpoint(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    @RequestMapping("/twitterId")
    public Flux<Publisher<? extends List<TwitterApiResponse>>> getTwitterId() {
        log.info("Testing testing...");
        return twitterApi.getTwitterTrendingCard();
    }

}
