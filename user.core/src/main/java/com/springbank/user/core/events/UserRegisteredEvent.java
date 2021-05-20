package com.springbank.user.core.events;

import com.springbank.user.core.models.User;

import lombok.*;

@Builder
@Data
public class UserRegisteredEvent {
	private String id;
	private User user;
}
