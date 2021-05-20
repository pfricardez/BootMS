package com.springbank.user.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.springbank.user.core.models.User;

import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Data
@Builder
public class RegisterUserCommand {
	@TargetAggregateIdentifier
	private String id;
	@NotNull(message="No user details are supplied")
	@Valid
	private User user;
}
