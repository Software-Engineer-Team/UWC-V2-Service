package com.UWCV2Service.config;

import com.UWCV2Service.listener.CascadingMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoConfig
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.UWCV2Service.repository")
public class MongoConfig {
  @Bean
  public CascadingMongoEventListener cascadingMongoEventListener() {
    return new CascadingMongoEventListener();
  }
}
