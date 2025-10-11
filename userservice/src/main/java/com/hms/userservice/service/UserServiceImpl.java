package com.hms.userservice.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.userservice.config.ErrorMessagesConfig;
import com.hms.userservice.dto.LoginDTO;
import com.hms.userservice.dto.UserDTO;
import com.hms.userservice.entity.User;
import com.hms.common_resource.exception.HmsException;
import com.hms.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional  // To handle lazy loading issues and ensure data integrity (ACID properties)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final ErrorMessagesConfig errorMessages;

  private final ApiService apiService;

  @Override
  public void registerUser(UserDTO userDTO) throws HmsException {
    Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
    if(opt.isPresent())
      throw new HmsException(errorMessages.getMessage("USER_ALREADY_EXIST"));
    
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    Long profileId = apiService.addProfile(userDTO).block();
    if(profileId == null) throw new HmsException(errorMessages.getMessage("INVALID_ROLE"));
    userDTO.setProfileId(profileId != null ? profileId.longValue() : null);
    userRepository.save(userDTO.toEntity());
  }

  @Override
  public UserDTO loginUser(LoginDTO loginDTO) throws HmsException{
    User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new HmsException(errorMessages.getMessage("USER_NOT_FOUND")));
    if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new HmsException(errorMessages.getMessage("INVALID_CREDENTIALS"));
    user.setPassword(null);

    return user.toDTO();
  }

  @Override
  public UserDTO getUserById(Long id) throws HmsException {
    return userRepository.findById(id).orElseThrow(() -> new HmsException(errorMessages.getMessage("USER_NOT_FOUND"))).toDTO();
  }

  @Override
  public void updateUser(UserDTO userDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public UserDTO getUser(String email) throws HmsException {
    return userRepository.findByEmail(email).orElseThrow(() -> new HmsException(errorMessages.getMessage("USER_NOT_FOUND"))).toDTO();
  }
  
}
