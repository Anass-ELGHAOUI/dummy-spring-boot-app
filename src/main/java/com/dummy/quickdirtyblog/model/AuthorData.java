package com.dummy.quickdirtyblog.model;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import lombok.Builder;

@Builder
public record AuthorData(Long id, String firstName, String lastName) {

  public AuthorData(AuthorEntity author) {
    this(author.getId(), author.getFirstName(), author.getLastName());
  }
}
