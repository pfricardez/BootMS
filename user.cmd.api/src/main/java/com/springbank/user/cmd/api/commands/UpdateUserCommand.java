package com.springbank.user.cmd.api.commands;

import javax.validation.constraints.*;
import javax.validation.*;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.springbank.user.core.models.User;

import lombok.*;

@Builder
@Data
public class UpdateUserCommand {
	@TargetAggregateIdentifier
	private String id;
	@NotNull(message="No user details are supplied")
	@Valid
	private User user;
}
