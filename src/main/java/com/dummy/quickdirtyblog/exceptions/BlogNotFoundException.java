package com.dummy.quickdirtyblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {

  public BlogNotFoundException(String message) {
    super(message);
  }
}
