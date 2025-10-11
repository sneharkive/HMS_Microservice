package com.hms.profileservice.service;

import org.springframework.stereotype.Service;

import com.hms.common_resource.exception.HmsException;
import com.hms.profileservice.dto.DoctorDTO;

@Service
public interface DoctorService {
  public Long addDoctor(DoctorDTO doctorDTO) throws HmsException;
  public DoctorDTO getDoctorById(Long id) throws HmsException;
}
