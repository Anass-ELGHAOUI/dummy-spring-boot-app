package com.dummy.quickdirtyblog;

import static org.springframework.http.HttpMethod.POST;

import com.dummy.quickdirtyblog.model.BlogData;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestsUtils {

  public static final class Client {

    private final RestTemplate rest;

    public Client(RestTemplateBuilder rest) {
      this.rest = rest.build();
    }

    public Client(int serverPort) {
      this(
          new RestTemplateBuilder()
              .rootUri("http://localhost:" + serverPort)
              .defaultHeader("username", "guest"));
    }

    public BlogData postBlog(BlogData blogData) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<BlogData> request = new HttpEntity<>(blogData, headers);
      return assertSuccess(
          rest.exchange("/api/v1/blogs", POST, request, BlogData.class), 201);
    }

    public static <T> T assertSuccess(ResponseEntity<T> resp, int responseCodeExpected) {
      try (var softly = new AutoCloseableSoftAssertions()) {
        softly.assertThat(resp.getStatusCode().value()).isEqualTo(responseCodeExpected);
        softly.assertThat(resp.getBody()).isNotNull();
        return resp.getBody();
      }
    }
  }
}
