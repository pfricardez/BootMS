package com.springbank.user.cmd.api.controllers;

import java.util.*;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.dto.*;

import javax.validation.*;

@RestController
@RequestMapping(path="/api/v1/registerUser")
public class RegisterUserController {
	private final CommandGateway commandGateway;

	@Autowired
	public RegisterUserController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
		var id = UUID.randomUUID().toString();
		
		try {
			command.setId(id);
			commandGateway.sendAndWait(command);
			return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully registered."), HttpStatus.CREATED);
		}
		catch(Exception ex) {
			var safeErrorMessage = "Error while processing register user request for id - " + id;
			System.out.println(ex.toString());
			return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
