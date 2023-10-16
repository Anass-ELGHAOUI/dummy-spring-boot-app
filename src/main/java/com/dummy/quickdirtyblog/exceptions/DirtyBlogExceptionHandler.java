package com.dummy.quickdirtyblog.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DirtyBlogExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(DirtyBlogExceptionHandler.class);

  @ExceptionHandler(value = {BlogNotFoundException.class})
  protected ResponseEntity<Object> handleBlogNotFoundException(BlogNotFoundException ex) {
    log.warn("Blog not found: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}
