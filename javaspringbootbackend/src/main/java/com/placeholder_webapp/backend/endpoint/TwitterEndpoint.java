package com.placeholder_webapp.backend.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.placeholder_webapp.backend.api.DefluxedTwitterApi;
import com.placeholder_webapp.backend.api.TwitterApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
public class TwitterEndpoint {

  private DefluxedTwitterApi defluxedTwitterApi;

  public TwitterEndpoint( DefluxedTwitterApi defluxedTwitterApi) {
    this.defluxedTwitterApi = defluxedTwitterApi;
  }

  @RequestMapping("/deflux")
  public void getTwitterDeflux() throws JsonProcessingException {
    defluxedTwitterApi.getTrending();
  }
}
