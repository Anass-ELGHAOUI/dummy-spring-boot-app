package com.dummy.quickdirtyblog.controller;

import com.dummy.quickdirtyblog.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorController(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }
}
