package com.ag.service;

import com.ag.model.LoginRequestModel;
import com.ag.model.SignUpRequestModel;

public interface UserServices {
	
	public String login(LoginRequestModel loginRequest);
	public String signUp(SignUpRequestModel signUpRequest);
}
