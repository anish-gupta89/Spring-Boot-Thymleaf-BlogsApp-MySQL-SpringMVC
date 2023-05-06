package com.ag.model;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class ValidationRequest {
	
	@Email(message = "Email should be in proper format.")
	String email;
}
