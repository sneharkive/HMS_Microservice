package com.hms.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hms.userservice.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
  Optional<User> findByEmail(String email);
  
}
