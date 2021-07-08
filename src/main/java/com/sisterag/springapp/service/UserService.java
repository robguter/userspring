package com.sisterag.springapp.service;

import com.sisterag.springapp.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	
	public User createUser(User user) throws Exception;

	public User getUserById(Long id) throws Exception;

	public User updateUser(User user) throws Exception;

	public User deleteUser(Long id) throws Exception;
	
}
