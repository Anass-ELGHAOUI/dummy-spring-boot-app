package com.dummy.quickdirtyblog.model;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import java.util.List;
import lombok.Builder;

@Builder
public record AuthorData(Long id, String name, String email, List<String> roles) {

  public AuthorData(AuthorEntity author) {
    this(author.getId(), author.getName(), author.getEmail(), author.getRoles());
  }
}
