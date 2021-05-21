package com.springbank.user.cmd.api.commands;

import org.axonframework.modelling.command.*;

import lombok.*;

@Data
@AllArgsConstructor
public class RemoveUserCommand {
	@TargetAggregateIdentifier
	private String id;
}
