package com.springbank.user.cmd.api.aggregates;

import java.util.*;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.springbank.user.cmd.api.commands.*;
import com.springbank.user.cmd.api.security.PasswordEncoder;
import com.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.springbank.user.core.events.*;
import com.springbank.user.core.models.User;

@Aggregate
public class UserAggregate {
	@AggregateIdentifier
	private String id;
	private User user;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserAggregate() {
		passwordEncoder = new PasswordEncoderImpl();
	}
	
	@CommandHandler
	public UserAggregate(RegisterUserCommand command) {
		var newUser = command.getUser();
		newUser.setId(command.getId());
		var password = newUser.getAccount().getPassword();
		passwordEncoder = new PasswordEncoderImpl();
		var hashedPassword = passwordEncoder.hashPassword(password);
		newUser.getAccount().setPassword(hashedPassword);
		
		var event = UserRegisteredEvent.builder()
				.id(command.getId())
				.user(newUser)
				.build();
		
		AggregateLifecycle.apply(event);
	}
	
	@CommandHandler
	public void handle(UpdateUserCommand command) { 
		var updateUser = command.getUser();
		updateUser.setId(command.getId());
		var password = updateUser.getAccount().getPassword();
		var hashedPassword = passwordEncoder.hashPassword(password);
		updateUser.getAccount().setPassword(hashedPassword);
		
		var event = UserUpdatedEvent.builder()
				.id(UUID.randomUUID().toString())
				.user(updateUser)
				.build();
		
		AggregateLifecycle.apply(event);
	}
	
	@CommandHandler
	public void handle(RemoveUserCommand command) { 
		var event = new UserRemovedEvent();
		event.setId(command.getId());
		
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(UserRegisteredEvent event) { 
		this.id = event.getId();
		this.user = event.getUser();
	}
	
	@EventSourcingHandler
	public void on(UserUpdatedEvent event) { 
		this.user = event.getUser();
	}
	
	@EventSourcingHandler
	public void on(UserRemovedEvent event) { 
		AggregateLifecycle.markDeleted();
	}
}
