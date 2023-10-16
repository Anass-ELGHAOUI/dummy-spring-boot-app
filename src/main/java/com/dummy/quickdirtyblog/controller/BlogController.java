package com.dummy.quickdirtyblog.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dummy.quickdirtyblog.model.BlogData;
import com.dummy.quickdirtyblog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

  private final BlogService blogService;

  @Autowired
  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping("/{blogId}")
  BlogData getBlog(@PathVariable Long blogId) {
    return blogService.getBlog(blogId);
  }

  @Operation(summary = "Save a new blog in db.")
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<BlogData> postBlog(
      @Valid @RequestBody BlogData blogData, @AuthenticationPrincipal OAuth2User principal) {
    return new ResponseEntity<>(blogService.postBlog(blogData, principal), HttpStatus.CREATED);
  }

  @Operation(summary = "Search blogs by title with pagination.")
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<BlogData> getBlogs(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "20") int limit,
      @RequestParam(value = "published", defaultValue = "false") boolean published) {
    return blogService.listBlogs(title, offset, limit, published);
  }
}
