package com.globallogic.bci.exercise.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.bci.exercise.dto.SignUpDto;
import com.globallogic.bci.exercise.model.User;
import com.globallogic.bci.exercise.model.response.LoginResponse;
import com.globallogic.bci.exercise.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LoginResponse> signUp(@Valid @RequestBody SignUpDto signUpValues) throws Exception {
		final User createdUser = userService.signUp(signUpValues);
		final LoginResponse response = new LoginResponse();
		response.setCreated(LocalDateTime.now());
		response.setId(createdUser.getId());
		//TODO: Completar datos de sesión y token
		return ResponseEntity.ok(response);
	}
}
