package com.hms.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hms.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  
}
