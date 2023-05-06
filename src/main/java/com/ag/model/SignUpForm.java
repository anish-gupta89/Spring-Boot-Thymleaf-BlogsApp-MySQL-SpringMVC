package com.ag.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpForm {

	@Email(message = "Email should be in proper format.")
	String userEmail;

	@NotBlank(message = "Name cannot be left blank.")
	String userName;
	
	@NotBlank(message = "Phone Number cannot be left blank.")
	String phoneNumber;
}
