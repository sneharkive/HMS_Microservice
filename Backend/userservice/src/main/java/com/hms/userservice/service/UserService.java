package com.hms.userservice.service;

import org.springframework.stereotype.Service;

import com.hms.userservice.dto.LoginDTO;
import com.hms.userservice.dto.UserDTO;
import com.hms.userservice.exception.HmsException;

@Service
public interface UserService {
  public void registerUser(UserDTO userDTO) throws HmsException;
  public UserDTO loginUser(LoginDTO loginDTO) throws HmsException;
  public UserDTO getUserById(Long id) throws HmsException;
  public void updateUser(UserDTO userDTO);
  public UserDTO getUser(String email) throws HmsException;  
}
