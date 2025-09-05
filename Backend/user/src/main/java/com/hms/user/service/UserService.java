package com.hms.user.service;

import org.springframework.stereotype.Service;

import com.hms.user.dto.UserDTO;
import com.hms.user.exception.HmsException;

@Service
public interface UserService {
  public void registerUser(UserDTO userDTO) throws HmsException;
  public UserDTO loginUser(UserDTO userDTO) throws HmsException;
  public UserDTO getUserById(Long id) throws HmsException;
  public void updateUser(UserDTO userDTO);
}
