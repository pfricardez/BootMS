package com.springbank.user.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="users")
public class User {

	@Id
	private String id;
	@NotEmpty(message="firstname is mandatory")
	private String firstname;
	@NotEmpty(message="lastname is mandatory")
	private String lastname;
	@Email(message="Provide a valid email address")
	private String emailAddress;
	@NotNull(message="Provide account credentials")
	private Account account;
}
