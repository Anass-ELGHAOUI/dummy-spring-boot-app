package com.dummy.quickdirtyblog.utils;

import static lombok.AccessLevel.PRIVATE;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import com.dummy.quickdirtyblog.model.AuthorData;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class AuthorDataMapper {
  public static AuthorData convertAuthorEntity(AuthorEntity authorEntity) {
    if (authorEntity == null) {
      return null;
    }
    return new AuthorData(authorEntity);
  }

  public static AuthorEntity convertAuthorData(AuthorData authorData) {
    if (authorData == null) {
      return null;
    }
    return AuthorEntity.builder()
        .id(authorData.id())
        .firstName(authorData.firstName())
        .lastName(authorData.lastName())
        .build();
  }
}
