package com.dummy.quickdirtyblog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.dummy.quickdirtyblog.entities.BlogEntity;
import com.dummy.quickdirtyblog.model.BlogData;
import com.dummy.quickdirtyblog.repositories.AuthorRepository;
import com.dummy.quickdirtyblog.repositories.BlogRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {QuickDirtyBlogApplication.class})
class QuickDirtyBlogApplicationTests {
  private static final Logger log = LoggerFactory.getLogger(QuickDirtyBlogApplicationTests.class);

  private static TestsUtils.Client client;

  private static final PostgreSQLContainer postgreSQLContainer;

  static {
    postgreSQLContainer =
        new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");
    postgreSQLContainer.start();
  }

  @DynamicPropertySource
  public static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @BeforeAll
  public static void beforeAll(@LocalServerPort int serverPort) {
    log.info("Connect test client to http://localhost:{}", serverPort);
    client = new TestsUtils.Client(serverPort);
  }

  @MockBean BlogRepository blogRepository;
  @MockBean AuthorRepository authorRepository;

  @Test
  void should_save_blog() {
    // GIVEN
    BlogData blogData = BlogData.builder().title("test").content("content").draft(false).build();

    // WHEN
    when(blogRepository.save(any()))
        .thenReturn(BlogEntity.builder().title("test").content("content").draft(false).build());

    // THEN
    BlogData res = client.postBlog(blogData);
    assertThat(res).usingRecursiveComparison().isEqualTo(blogData);
  }
}
