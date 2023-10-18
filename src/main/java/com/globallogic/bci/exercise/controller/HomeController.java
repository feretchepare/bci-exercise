package com.globallogic.bci.exercise.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.bci.exercise.dto.SignUpDto;
import com.globallogic.bci.exercise.login.JwtUtil;
import com.globallogic.bci.exercise.model.User;
import com.globallogic.bci.exercise.model.response.SignUpResponse;
import com.globallogic.bci.exercise.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpDto signUpValues,
			final BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getAllErrors().toString());
		}
		final User createdUser = userService.signUp(signUpValues);
		final SignUpResponse response = new SignUpResponse();
		response.setCreated(LocalDateTime.now());
		response.setId(createdUser.getId());
		response.setToken(jwtUtil.createToken(createdUser));
		response.setIsActive(Boolean.TRUE);
		response.setLastLogin(LocalDateTime.now());
		return ResponseEntity.ok(response);
	}
}
