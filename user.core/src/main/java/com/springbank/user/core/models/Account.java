package com.springbank.user.core.models;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
	
	private String username;
	private String password;
	private List<Role> roles;

}
