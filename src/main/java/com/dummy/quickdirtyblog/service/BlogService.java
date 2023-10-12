package com.dummy.quickdirtyblog.service;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import com.dummy.quickdirtyblog.entities.BlogEntity;
import com.dummy.quickdirtyblog.model.BlogData;
import com.dummy.quickdirtyblog.repositories.BlogRepository;
import com.dummy.quickdirtyblog.utils.BlogDataMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {

  private final BlogRepository blogRepository;

  public BlogService(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  public List<BlogData> findByTitle(String title, int offset, int limit) {
    log.info("find blogs by title {} with offset={}, limit={}", title, offset, limit);
    PageRequest pageRequest = PageRequest.of(offset, limit);
    Page<BlogEntity> blogEntity =
        blogRepository.findByTitleContainingIgnoreCase(title, pageRequest);
    return blogEntity.get().map(BlogDataMapper::convertBlogEntity).toList();
  }

  public BlogData postBlog(BlogData blog) {
    log.info("Start saving {}", blog);
    return BlogDataMapper.convertBlogEntity(
        blogRepository.save(
            BlogDataMapper.convertBlogData(blog).toBuilder()
                .author(AuthorEntity.builder().firstName("anas").lastName("elghaoui").build())
                .build()));
  }
}
