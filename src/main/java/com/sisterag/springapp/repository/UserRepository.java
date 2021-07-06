package com.sisterag.springapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sisterag.springapp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	
}
