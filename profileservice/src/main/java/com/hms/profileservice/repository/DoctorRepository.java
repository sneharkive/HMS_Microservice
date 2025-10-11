package com.hms.profileservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.profileservice.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  Optional<Doctor> findByEmail(String email);
  Optional<Doctor> findByLicenseNo(String licenseNo);
  
} 
