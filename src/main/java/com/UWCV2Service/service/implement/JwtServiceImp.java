package com.UWCV2Service.service.implement;

import com.UWCV2Service.service.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * JwtServiceImp
 */
@Service
public class JwtServiceImp implements JwtService {
  private final String SECRET_KEY = "secret";
  private final String CLAIM_NAME = "roles";
  private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
  // Algorithm.HMAC256(this.env.getProperty("secret-key", "secret"));

  public String createToken(List<Object> claims, String subject, String issuer,
                            Long time) {
    return JWT.create()
        .withSubject(subject)
        .withExpiresAt(new Date(System.currentTimeMillis() + time))
        .withClaim(CLAIM_NAME, claims)
        .withIssuer(issuer)
        .sign(algorithm);
  }

  public DecodedJWT decodedJWT(String token) throws Exception {
    JWTVerifier verifier = JWT.require(this.algorithm).build();
    return verifier.verify(token);
  }

  public String extractSubject(String token) throws Exception {
    return this.decodedJWT(token).getSubject();
  }

  public List<String> extractClaim(String token) throws Exception {
    return this.decodedJWT(token)
        .getClaim(this.CLAIM_NAME)
        .asList(String.class);
  }

  public Algorithm getAlgorithm() { return this.algorithm; }
}
