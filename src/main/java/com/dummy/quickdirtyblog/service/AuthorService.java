package com.dummy.quickdirtyblog.service;

import static com.dummy.quickdirtyblog.utils.AuthorDataMapper.convertAuthorData;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import com.dummy.quickdirtyblog.model.AuthorData;
import com.dummy.quickdirtyblog.repositories.AuthorRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public AuthorEntity getAuthor(AuthorData authorData) {
    Optional<AuthorEntity> author =
        authorRepository.findAuthorByNameContainsIgnoreCase(authorData.name());
    return author.orElse(authorRepository.save(convertAuthorData(authorData)));
  }
}
