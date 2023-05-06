package com.ag.model;

import lombok.Data;

@Data
public class UnlockForm {

	String email;

	String tempPassword;

	String password;

	String confirmPassword;
}
