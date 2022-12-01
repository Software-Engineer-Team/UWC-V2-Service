package com.UWCV2Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UwcV2ServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UwcV2ServiceApplication.class, args);
  }

  // @Bean
  // public PasswordEncoder passwordEncoder() {
  //   return new BCryptPasswordEncoder();
  // }
}
