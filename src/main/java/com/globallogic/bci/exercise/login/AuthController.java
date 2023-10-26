package com.globallogic.bci.exercise.login;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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
@AllArgsConstructor
public class AuthController {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService customUserDetailsService;
	private final AuthenticationManager authenticationManager;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginReq)
			throws Exception {
		try {
			CustomUserDetails userFound = customUserDetailsService.loadUserByUsername(loginReq.getEmail());
			final Authentication authenticated = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userFound.getUsername(), loginReq.getPassword()));
			final CustomUserDetails userDetails = (CustomUserDetails) authenticated.getPrincipal();
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
			response.setIsActive(userDetails.isEnabled());
			response.setLastLogin(LocalDateTime.now());
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		} catch (Exception e) {
			throw new Exception("Invalid username or password");
		}
	}

}
