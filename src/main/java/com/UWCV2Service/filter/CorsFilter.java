package com.UWCV2Service.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CorsFilter
 */
public class CorsFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
                       FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse)res;
    HttpServletRequest request = (HttpServletRequest)req;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods",
                       "GET,POST,DELETE,PUT,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Max-Age", "180");
    filterChain.doFilter(request, response);
  }
}
