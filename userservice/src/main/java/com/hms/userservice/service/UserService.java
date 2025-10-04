package com.hms.userservice.service;

import org.springframework.stereotype.Service;

import com.hms.common_resource.exception.HmsException;
import com.hms.userservice.dto.LoginDTO;
import com.hms.userservice.dto.UserDTO;


@Service
public interface UserService {
  public void registerUser(UserDTO userDTO) throws HmsException;
  public UserDTO loginUser(LoginDTO loginDTO) throws HmsException;
  public UserDTO getUserById(Long id) throws HmsException;
  public void updateUser(UserDTO userDTO);
  public UserDTO getUser(String email) throws HmsException;  
}
