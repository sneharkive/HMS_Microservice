package com.hms.userservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.userservice.dto.LoginDTO;
import com.hms.userservice.dto.ResponseDTO;
import com.hms.userservice.dto.UserDTO;
import com.hms.userservice.exception.HmsException;
import com.hms.userservice.jwt.JwtUtil;
import com.hms.userservice.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserAPI {

  @Autowired
  private UserService userService;

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws HmsException {
      userService.registerUser(userDTO);
      
      return new ResponseEntity<>(new ResponseDTO("Account Created"), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<String> createAuthToken(@RequestBody LoginDTO loginDTO) throws HmsException {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
      );
    } catch (AuthenticationException e) {
      throw new HmsException("INVALID_CREDENTIALS");
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails);
    
    return new ResponseEntity<>(jwt, HttpStatus.OK);
  }


  @GetMapping("/test")
  public ResponseEntity<String> test() {
      return new ResponseEntity<String>("Test successful", HttpStatus.OK);
  }
  
  
}
