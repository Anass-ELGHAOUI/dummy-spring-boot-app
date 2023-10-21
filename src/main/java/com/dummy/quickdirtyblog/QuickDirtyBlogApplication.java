package com.dummy.quickdirtyblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickDirtyBlogApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "application-standalone");
    SpringApplication.run(QuickDirtyBlogApplication.class, args);
  }
}
