package com.hms.profileservice.entity;

import java.time.LocalDate;

import com.hms.profileservice.dto.BloodGroup;
import com.hms.profileservice.dto.PatientDTO;

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
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Column(unique = true)
  private String email;
  
  private LocalDate dob;

  private String phone;

  private String address;

  @Column(unique = true)
  private String aadharNo;
  
  private BloodGroup bloodGroup;

  public PatientDTO toDTO(){
    return new PatientDTO(this.id, this.name, this.email, this.dob, this.phone, this.address, this.aadharNo, this.bloodGroup);
  }
}
