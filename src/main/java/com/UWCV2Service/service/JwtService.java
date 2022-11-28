package com.UWCV2Service.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.List;

/**
 * JwtService
 */
public interface JwtService {
  public String createToken(List<Object> claims, String subject, String issuer,
                            Long time);

  public DecodedJWT decodedJWT(String token) throws Exception;

  public String extractSubject(String token) throws Exception;

  public List<String> extractClaim(String token) throws Exception;

  public Algorithm getAlgorithm();
}
