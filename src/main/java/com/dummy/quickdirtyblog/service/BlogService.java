package com.dummy.quickdirtyblog.service;

import static com.dummy.quickdirtyblog.utils.BlogDataMapper.convertBlogEntity;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import com.dummy.quickdirtyblog.entities.BlogEntity;
import com.dummy.quickdirtyblog.exceptions.BlogNotFoundException;
import com.dummy.quickdirtyblog.exceptions.OperationNotAllowedException;
import com.dummy.quickdirtyblog.model.AuthorData;
import com.dummy.quickdirtyblog.model.BlogData;
import com.dummy.quickdirtyblog.repositories.BlogRepository;
import com.dummy.quickdirtyblog.utils.BlogDataMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {

  private final BlogRepository blogRepository;
  private final AuthorService authorService;

  @Autowired
  public BlogService(BlogRepository blogRepository, AuthorService authorService) {
    this.blogRepository = blogRepository;
    this.authorService = authorService;
  }

  public List<BlogData> listBlogs(String title, int offset, int limit, boolean published) {
    log.info(
        "find blogs by title {} with offset={}, limit={}, published={}",
        title,
        offset,
        limit,
        published);
    PageRequest pageRequest = PageRequest.of(offset, limit);
    Page<BlogEntity> blogEntity =
        isEmpty(title)
            ? blogRepository.findAllByDraftEquals(!published, pageRequest)
            : blogRepository.findByTitleContainingIgnoreCaseAndDraftEquals(
                title, !published, pageRequest);
    return blogEntity.get().map(BlogDataMapper::convertBlogEntity).toList();
  }

  public BlogData postBlog(BlogData blog) {
    log.info("Start saving {}", blog);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new OperationNotAllowedException("Unable to create the blog, user not authenticated");
    }
    AuthorEntity authorEntity =
        authorService.getAuthor(AuthorData.builder().name(authentication.getName()).build());
    return convertBlogEntity(
        blogRepository.save(
            BlogDataMapper.convertBlogData(blog).toBuilder().author(authorEntity).build()));
  }

  public BlogData getBlog(Long blogId) {
    log.info("get blog {}", blogId);
    Optional<BlogEntity> blog = blogRepository.findById(blogId);
    return blog.map(BlogDataMapper::convertBlogEntity)
        .orElseThrow(() -> new BlogNotFoundException("Found no blog with id: " + blogId));
  }
}
