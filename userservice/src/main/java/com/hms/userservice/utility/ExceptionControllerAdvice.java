package com.hms.userservice.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hms.common_resource.exception.HmsException;
import com.hms.common_resource.utility.ErrorInfo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @Autowired
  Environment environment;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorInfo> exceptionHandler(Exception e) {
    ErrorInfo error = new ErrorInfo("Some error occurred." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HmsException.class)
  public ResponseEntity<ErrorInfo> HmsExceptionHandler(Exception e) {
    ErrorInfo error = new ErrorInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
  public ResponseEntity<ErrorInfo> validationExceptionHandler(Exception e) {
    String errorMsg;

    if (e instanceof MethodArgumentNotValidException manv) {
      errorMsg = manv.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
          .collect(Collectors.joining(", "));
    } else {
      ConstraintViolationException cve = (ConstraintViolationException) e;
      errorMsg = cve.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
          .collect(Collectors.joining(", "));
    }
    ErrorInfo error = new ErrorInfo(errorMsg, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
