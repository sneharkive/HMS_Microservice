package com.hms.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.userservice.dto.LoginDTO;
import com.hms.userservice.dto.UserDTO;
import com.hms.userservice.entity.User;
import com.hms.userservice.exception.HmsException;
import com.hms.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void registerUser(UserDTO userDTO) throws HmsException {
    Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
    if(opt.isPresent())
      throw new HmsException("USER_ALREADY_EXIST");
    
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(userDTO.toEntity());
  }

  @Override
  public UserDTO loginUser(LoginDTO loginDTO) throws HmsException{
    User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new HmsException("USER_NOT_FOUND"));
    if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new HmsException("INVALID_CREDENTIALS");
    user.setPassword(null);

    return user.toDTO();
  }

  @Override
  public UserDTO getUserById(Long id) throws HmsException {
    return userRepository.findById(id).orElseThrow(() -> new HmsException("USER_NOT_FOUND")).toDTO();
  }

  @Override
  public void updateUser(UserDTO userDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public UserDTO getUser(String email) throws HmsException {
    return userRepository.findByEmail(email).orElseThrow(() -> new HmsException("USER_NOT_FOUND")).toDTO();
  }
  
}
