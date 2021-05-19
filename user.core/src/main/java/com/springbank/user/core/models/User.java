package com.springbank.user.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="users")
public class User {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String emailAddress;
	private Account account;
}
