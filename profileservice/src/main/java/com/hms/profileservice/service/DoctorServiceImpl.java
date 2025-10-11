package com.hms.profileservice.service;

import org.springframework.stereotype.Service;

import com.hms.common_resource.exception.HmsException;
import com.hms.profileservice.dto.DoctorDTO;
import com.hms.profileservice.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

  private final DoctorRepository doctorRepository;
  
  @Override
  public Long addDoctor(DoctorDTO doctorDTO) throws HmsException {
    if(doctorDTO.getEmail()!= null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent())
      throw new HmsException("Doctor already exists!!");
      
    if(doctorDTO.getLicenseNo() != null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent())
      throw new HmsException("Doctor already exists!!");

    return doctorRepository.save(doctorDTO.toEntity()).getId();
  }

  @Override
  public DoctorDTO getDoctorById(Long id) throws HmsException {
    return doctorRepository.findById(id).orElseThrow(() -> new HmsException("Doctor Not Found!")).toDTO();
  }
  
}
