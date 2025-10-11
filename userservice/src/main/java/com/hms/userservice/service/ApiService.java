package com.hms.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hms.userservice.dto.Roles;
import com.hms.userservice.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiService {

  private final WebClient.Builder webClientBuilder;

  public Mono<Long> addProfile(UserDTO userDTO) {
    if(userDTO.getRole().equals(Roles.DOCTOR)){
      return webClientBuilder.build()
        .post()
        .uri("http://localhost:8080/profile/doctor/add")
        .bodyValue(userDTO)
        .retrieve()
        .bodyToMono(Long.class);
    }
    else if(userDTO.getRole().equals(Roles.PATIENT)){
      return webClientBuilder.build()
        .post()
        .uri("http://localhost:8080/profile/patient/add")
        .bodyValue(userDTO)
        .retrieve()
        .bodyToMono(Long.class);
    }
    return null;
  
  }
}