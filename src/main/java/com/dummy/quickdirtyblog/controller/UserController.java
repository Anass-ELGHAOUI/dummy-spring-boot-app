package com.dummy.quickdirtyblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @GetMapping(path = "/login")
  public String callback(@RequestParam String code) {
    return "Hi anas";
  }
}
