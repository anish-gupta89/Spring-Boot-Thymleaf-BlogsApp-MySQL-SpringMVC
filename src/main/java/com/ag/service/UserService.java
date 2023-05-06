package com.ag.service;

import com.ag.model.LoginForm;
import com.ag.model.SignUpForm;
import com.ag.model.UnlockForm;

public interface UserService {
	
	public String login(LoginForm formData);
	
	public String signUp(SignUpForm formData);
	
	public String unlockAccount(UnlockForm formData);
	
	public String forgotPwd(UnlockForm formData);
}
