package com.ag.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {

	@Email(message = "Email should be in proper format.")
	@NotEmpty(message = "Email should not be empty.")
	String email;

	@NotBlank(message = "Password cannot be left blank.")
	String password;
}
