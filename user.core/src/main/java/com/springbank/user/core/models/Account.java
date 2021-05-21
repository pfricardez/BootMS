package com.springbank.user.core.models;

import java.util.List;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
	@Size(min=2, message="username must have a minimun of 2 characters")
	private String username;
	@Size(min=7, message="password must have a minimun of 7 characters")
	private String password;
	@NotNull(message="Specify at least 1 user role")
	private List<Role> roles;
}
