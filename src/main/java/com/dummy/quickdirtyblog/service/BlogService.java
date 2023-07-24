package com.dummy.quickdirtyblog.service;

import com.dummy.quickdirtyblog.model.Blog;
import com.dummy.quickdirtyblog.repositories.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

  private final BlogRepository blogRepository;

  public BlogService(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  public Page<Blog> findByTitle(String title, int offset, int limit) {
    PageRequest pageRequest = PageRequest.of(offset, limit);
    return blogRepository.findByTitleContainingIgnoreCase(title, pageRequest);
  }

  public Blog postBlog(Blog blog) {
    return blogRepository.save(blog);
  }
}
