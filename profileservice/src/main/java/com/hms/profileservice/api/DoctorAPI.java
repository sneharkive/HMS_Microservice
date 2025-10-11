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
import com.hms.profileservice.dto.DoctorDTO;
import com.hms.profileservice.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @CrossOrigin
@RequestMapping("/profile/doctor")
@Validated
public class DoctorAPI {
  
  private final DoctorService doctorService;

  @PostMapping("/add")
  public ResponseEntity<Long> addPatient(@RequestBody @Valid DoctorDTO doctorDTO) throws HmsException {
      
    return new ResponseEntity<>(doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<DoctorDTO> getPatientById(@PathVariable Long id) throws HmsException {
      
    return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
  }

}
