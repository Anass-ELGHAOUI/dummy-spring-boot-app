package com.dummy.quickdirtyblog.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dummy.quickdirtyblog.model.AuthorData;
import com.dummy.quickdirtyblog.repositories.AuthorRepository;
import com.dummy.quickdirtyblog.utils.AuthorDataMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Operation(summary = "Search authors by name")
  @GetMapping(path = "/{name}", produces = APPLICATION_JSON_VALUE)
  //@PreAuthorize("hasRole('client_admin')")
  public List<AuthorData> getAuthors(@PathVariable("name") String name) {
    return authorRepository.findAuthorByNameContainsIgnoreCase(name).stream()
        .map(AuthorDataMapper::convertAuthorEntity)
        .toList();
  }
}
