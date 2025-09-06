package com.hms.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import org.springframework.http.HttpHeaders;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {

  @Value("${spring.jwt.secret}")
  private String SECRET_KEY;

  @Value("${spring.jwt.expiration}")
  private long EXPIRATION_TIME;

  public static class Config {}

  public TokenFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      String path = exchange.getRequest().getPath().toString();
      if(path.equals("/user/login") || path.equals("/user/register")){
        // return chain.filter(exchange);
        return chain.filter(exchange.mutate().request(r->r.header("X-Secret-Key", "SECRET_KEY")).build());

      }
      HttpHeaders headers = exchange.getRequest().getHeaders();
      if(!headers.containsKey(HttpHeaders.AUTHORIZATION)){
        throw new RuntimeException("Authorization header is missing");
      }
      String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
      if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        throw new RuntimeException("Authorization header is Invalid");
      }
      String token = authHeader.substring(7);
      try{
        Claims claims = io.jsonwebtoken.Jwts.parserBuilder()
          .setSigningKey(SECRET_KEY.getBytes())
          .build()
          .parseClaimsJws(token)
          .getBody();
          exchange = exchange.mutate().request(r->r.header("X-Secret-Key", String.valueOf(claims.get("SECRET_KEY")))).build();

      } catch (Exception e) {
        throw new RuntimeException("Invalid JWT token");
      }
      return chain.filter(exchange);
    };
  }

}
