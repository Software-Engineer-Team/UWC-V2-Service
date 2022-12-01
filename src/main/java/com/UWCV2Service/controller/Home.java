package com.UWCV2Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home
 */
@RestController
@RequestMapping("/")
public class Home {
  @GetMapping("/")
  private String home() {
    return "Welcome to our server";
  }
}
