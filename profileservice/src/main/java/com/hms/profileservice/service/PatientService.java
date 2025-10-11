package com.hms.profileservice.service;

import org.springframework.stereotype.Service;

import com.hms.common_resource.exception.HmsException;
import com.hms.profileservice.dto.PatientDTO;

@Service
public interface PatientService {
  public Long addPatient(PatientDTO patientDTO) throws HmsException;
  public PatientDTO getPatientById(Long id) throws HmsException;

}
