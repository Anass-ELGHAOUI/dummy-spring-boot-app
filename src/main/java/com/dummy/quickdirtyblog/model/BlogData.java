package com.dummy.quickdirtyblog.model;

import com.dummy.quickdirtyblog.entities.BlogEntity;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BlogData(
        Long id, String title, String content, LocalDateTime creationDate, boolean draft) {

  public BlogData(BlogEntity blogEntity) {
    this(
        blogEntity.getId(),
        blogEntity.getTitle(),
        blogEntity.getContent(),
        blogEntity.getCreationDate(),
        blogEntity.isDraft());
  }
}
