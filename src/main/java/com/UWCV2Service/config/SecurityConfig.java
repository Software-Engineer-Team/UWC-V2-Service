package com.UWCV2Service.config;

import com.UWCV2Service.filter.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final ApplicationContext applicationContext;

  @Bean
  @Order(0)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors();
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(
        SessionCreationPolicy.STATELESS);
    http.addFilterBefore(new AuthorizationFilter(applicationContext),
                         UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  @Order(1)
  public SecurityFilterChain apiFilterChain(HttpSecurity http)
      throws Exception {
    http.authorizeHttpRequests((authorizeRequests) -> {
      authorizeRequests
          .requestMatchers("api/user/sign-in/**", "/api/user/sign-up/**",
                           "/api/token/refresh/**")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/api/role/save",
                           "/api/role/addRoleToUser")
          .hasAnyRole("ADMIN", "CREATOR", "USER")
          .requestMatchers(HttpMethod.GET, "/api/user/**", "/api/users")
          .hasAnyRole("ADMIN", "CREATOR", "USER")
          .anyRequest()
          .authenticated();
    });

    return http.build();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);

    return authProvider;
  }

  @Bean
  public AuthenticationManager
  authenticationManager(AuthenticationConfiguration authConfiguration)
      throws Exception {
    return authConfiguration.getAuthenticationManager();
  }
}
