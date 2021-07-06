package com.sisterag.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisterag.springapp.entity.User;
import com.sisterag.springapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repostr;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repostr.findAll();
	}

}
