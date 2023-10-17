package com.globallogic.bci.exercise.login;

import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
	private final AuthenticationManager authenticationManager;

	private JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;

	}

	@ResponseBody
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginReq)
			throws BadCredentialsException, Exception {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
			String email = authentication.getName();
			User user = new User();
			user.setEmail(email);
			String token = jwtUtil.createToken(user);
			LoginResponse response = new LoginResponse();
			response.setCreated(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
			response.setId(user.getId());
			response.setToken(token);
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		} catch (Exception e) {
			throw new Exception("Invalid username or password");
		}
	}

}
