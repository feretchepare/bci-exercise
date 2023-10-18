package com.globallogic.bci.exercise.login;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globallogic.bci.exercise.model.User;
import com.globallogic.bci.exercise.model.request.LoginRequest;
import com.globallogic.bci.exercise.model.response.LoginResponse;

@Controller
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginReq)
			throws BadCredentialsException, Exception {
		try {
			CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(loginReq.getEmail());
			User user = new User();
			user.setId(userDetails.getUser().getId());
			user.setEmail(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setName(userDetails.getUser().getName());
			user.setPhones(userDetails.getUser().getPhones());
			String token = jwtUtil.createToken(user);
			LoginResponse response = new LoginResponse();
			response.setCreated(LocalDateTime.now());
			response.setId(user.getId());
			response.setToken(token);
			response.setName(user.getName());
			response.setPassword(user.getPassword());
			response.setEmail(user.getEmail());
			response.setPhones(user.getPhones());
			response.setIsActive(Boolean.TRUE);
			response.setLastLogin(LocalDateTime.now());
			response.setPhones(user.getPhones());
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		} catch (Exception e) {
			throw new Exception("Invalid username or password");
		}
	}

}
