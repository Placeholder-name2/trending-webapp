package com.placeholder_webapp.backend;

import com.placeholder_webapp.backend.api.TwitterApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    TwitterApi twitterApi(WebClient webClient){
        return new TwitterApi(webClient);
    }

    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .build();
    }
}
