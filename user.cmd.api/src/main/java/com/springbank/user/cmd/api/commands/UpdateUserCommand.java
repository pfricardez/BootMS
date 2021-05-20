package com.springbank.user.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.springbank.user.core.models.User;

import lombok.*;

@Builder
@Data
public class UpdateUserCommand {
	@TargetAggregateIdentifier
	private String id;
	private User user;
}
