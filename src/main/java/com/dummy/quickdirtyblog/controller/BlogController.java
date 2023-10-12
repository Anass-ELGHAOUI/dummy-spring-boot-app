package com.dummy.quickdirtyblog.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dummy.quickdirtyblog.model.BlogData;
import com.dummy.quickdirtyblog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quick-dirty-blog/blog/v1")
public class BlogController {

  private final BlogService blogService;

  @Autowired
  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @Operation(summary = "Save a new blog in db.")
  @PostMapping(
      path = "/blogs",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<BlogData> postBlog(@RequestBody BlogData blogData) {
    return new ResponseEntity<>(blogService.postBlog(blogData), HttpStatus.CREATED);
  }

  @Operation(summary = "Search blogs by title with pagination.")
  @GetMapping(path = "/blogs", produces = APPLICATION_JSON_VALUE)
  public List<BlogData> getBlogs(
      @RequestParam("title") String title,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "20") int limit) {
    return blogService.findByTitle(title, offset, limit);
  }
}
