package com.dummy.quickdirtyblog.utils;

import static lombok.AccessLevel.PRIVATE;

import com.dummy.quickdirtyblog.entities.BlogEntity;
import com.dummy.quickdirtyblog.model.BlogData;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class BlogDataMapper {
  public static BlogData convertBlogEntity(BlogEntity blogEntity) {
    if (blogEntity == null) {
      return null;
    }
    return new BlogData(blogEntity);
  }

  public static BlogEntity convertBlogData(BlogData blogData) {
    if (blogData == null) {
      return null;
    }
    return BlogEntity.builder()
        .id(blogData.id())
        .title(blogData.title())
        .content(blogData.content())
        .draft(blogData.draft())
        .build();
  }
}
