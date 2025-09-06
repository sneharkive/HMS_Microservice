package com.hms.userservice.jwt;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;


@Component
public class JwtUtil {

  // Node command to generate a 512-bit secret key =>
  // node -e "console.log(require('crypto').randomBytes(64).toString('hex'))"

  @Value("${spring.jwt.secret}")
  private String SECRET_KEY;

  @Value("${spring.jwt.expiration}")
  private long EXPIRATION_TIME;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }

  // Add custom claims
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    CustomUserDetails user = (CustomUserDetails) userDetails;

    // Add custom claims
    claims.put("id", user.getId());
    claims.put("email", user.getEmail());
    claims.put("name", user.getName());
    claims.put("role", user.getRole());
    claims.put("SECRET_KEY", "SECRET_KEY"); 

    return doGenerateToken(claims, userDetails.getUsername());  
  }

  public String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

}
