package com.hms.profileservice.service;

import org.springframework.stereotype.Service;

import com.hms.common_resource.exception.HmsException;
import com.hms.profileservice.dto.PatientDTO;
import com.hms.profileservice.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;

  @Override
  public Long addPatient(PatientDTO patientDTO) throws HmsException {
    if(patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent())
      throw new HmsException("Patient already exists!!");
      
    if(patientDTO.getAadharNo() != null && patientRepository.findByAadharNo(patientDTO.getAadharNo()).isPresent())
      throw new HmsException("Patient already exists!!");

    return patientRepository.save(patientDTO.toEntity()).getId();
    
  }

  @Override
  public PatientDTO getPatientById(Long id) throws HmsException {
    return patientRepository.findById(id).orElseThrow(() -> new HmsException("Patient Not Found!")).toDTO();
  }
  
}
