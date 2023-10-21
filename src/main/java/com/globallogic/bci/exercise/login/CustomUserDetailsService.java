package com.globallogic.bci.exercise.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.globallogic.bci.exercise.model.User;
import com.globallogic.bci.exercise.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userFound = userRepository.findOneByEmail(email);
		if (!userFound.isPresent()) {
			throw new UsernameNotFoundException("User with email " + email + " do not exists");
		}
		final User user = userFound.get();
		return new CustomUserDetails(user);
	}

}
