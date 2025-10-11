package com.hms.userservice.entity;

import com.hms.userservice.dto.Roles;
import com.hms.userservice.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Column(unique = true)
  private String email;
  private String password;
  private Roles role;

  private Long profileId;

  public UserDTO toDTO(){
    return new UserDTO(this.id, this.name, this.email, this.password, this.role, this.profileId);
  }
  
}
