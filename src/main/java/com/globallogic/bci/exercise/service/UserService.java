package com.globallogic.bci.exercise.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.bci.exercise.dto.SignUpDto;
import com.globallogic.bci.exercise.model.Phone;
import com.globallogic.bci.exercise.model.User;
import com.globallogic.bci.exercise.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User signUp(SignUpDto signUpValues) throws Exception {
		if (userRepository.findOneByEmail(signUpValues.getEmail()).isPresent()) {
			throw new EntityExistsException("User already exists");
		}

		User user = new User();
		user.setName(signUpValues.getName());
		user.setEmail(signUpValues.getEmail());
		user.setPassword(passwordEncoder.encode(signUpValues.getPassword()));
		if (!signUpValues.getPhones().isEmpty()) {
			user.setPhones(new ArrayList<>(signUpValues.getPhones()));
		}
		return userRepository.save(user);
	}

}
