package com.ramonVal.ProjectSecurity.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
  @Value("${security.jwt.private.key}")
  private String privateKey;

  @Value("${security.jwt.user.generator}")
  private String userGenerator;

  public String generateToken(Authentication authentication) {
      Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

      String username = authentication.getPrincipal().toString();

      String authorities = authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

      String jwtToken = JWT.create()
        .withIssuer(this.userGenerator)
        .withSubject(username)
        .withClaim("authorities", authorities)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date (System.currentTimeMillis()))
        .sign(algorithm);

      return jwtToken;
    }

    public DecodedJWT verifyToken(String token) {
      try{
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer(this.userGenerator)
          .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
      }catch (JWTVerificationException exception) {
        throw new JWTVerificationException("Invalid JWT token");
      }
    }

    public String extractUsername(DecodedJWT token) {
      return token.getSubject();
    }

    public Claim getSpecificClaim (DecodedJWT decodedJWT, String claimName) {
      return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> returnAllClaims (DecodedJWT decodedJWT){
      return decodedJWT.getClaims();
  }


}
