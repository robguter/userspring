package com.sisterag.springapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisterag.springapp.entity.User;
import com.sisterag.springapp.repository.UserRepository;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Usuario no disponible");
		}
		Optional<User> emailFound = repository.findByEmail(user.getEmail());
		if (emailFound.isPresent()) {
			throw new Exception("Email no disponible");
		}
		return true;
	}
	private boolean checkPasswordValid(User user) throws Exception {
		if ( !user.getPassword().equals(user.getConfirmpassword())) {
			throw new Exception("Password y Confirm Password son diferentes");
		}
		return true;
	}


	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no se encontró") );
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstname(from.getFirstname());
		to.setLastname(from.getLastname());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		to.setPassword(from.getPassword());
	}

	@Override
	public User deleteUser(Long id) throws Exception {
		User user = getUserById(id);
		repository.delete(user);
		return null;
	}

}
