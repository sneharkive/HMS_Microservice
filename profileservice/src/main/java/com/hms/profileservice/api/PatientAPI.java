package com.hms.profileservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.common_resource.exception.HmsException;
import com.hms.profileservice.dto.PatientDTO;
import com.hms.profileservice.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile/patient")
@Validated
public class PatientAPI {

  private final PatientService patientService;

  @PostMapping("/add")
  public ResponseEntity<Long> addPatient(@RequestBody @Valid PatientDTO patientDTO) throws HmsException {
      
    return new ResponseEntity<>(patientService.addPatient(patientDTO), HttpStatus.CREATED);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws HmsException {
      
    return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
  }

}
