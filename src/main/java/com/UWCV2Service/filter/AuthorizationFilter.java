package com.UWCV2Service.filter;

import com.UWCV2Service.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * AuthorizationFilter
 */
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
  private final String BEARER_NAME = "Bearer ";
  private JwtService jwtService;

  public AuthorizationFilter(ApplicationContext ctx) {
    this.jwtService = ctx.getBean(JwtService.class);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {

    log.info(
        "===================AuthorizationFilter.doFilterInternal() is running!!!====================");
    Predicate<String> checkRequestUrl = (s)
        -> s.equals("/api/user/sign-in") || s.equals("/api/token/refresh") ||
               s.contains("/api/user/sign-up") ||
               s.contains("/api/user/get-code-again");
    if (checkRequestUrl.test(request.getRequestURI().toString())) {
      filterChain.doFilter(request, response);
      return;
    }

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    Predicate<String> checkAuthHeader =
        (s) -> s != null && s.startsWith(BEARER_NAME);

    if (!checkAuthHeader.test(authorizationHeader)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = authorizationHeader.substring(BEARER_NAME.length());
      String userName = this.jwtService.extractSubject(token);
      List<String> roles = this.jwtService.extractClaim(token);
      Collection<GrantedAuthority> authorities =
          roles.stream()
              .map((role) -> new SimpleGrantedAuthority(role))
              .collect(Collectors.toList());
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(userName, null, authorities);
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authenticationToken);
      log.warn("token: {}", token);
      log.warn("userName: {}", userName);
      log.warn("roles: {}", roles);
      filterChain.doFilter(request, response);

    } catch (Exception e) {
      log.error("Error login in: {}", e.getMessage());
      response.setHeader("error", e.getMessage());
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

      Map<String, String> error = new HashMap<>();
      error.put("error_message", e.getMessage());
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(response.getOutputStream(), error);
    }
  }
}
