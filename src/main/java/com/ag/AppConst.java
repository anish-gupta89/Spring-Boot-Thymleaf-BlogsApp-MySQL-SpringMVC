package com.ag;

public interface AppConst {

	public String INDEX = "index";
	public String LOGIN = "login";
	public String SIGN_UP = "signup";
	public String ADD_ENQ = "add-enquiry";
	public String DASHBOARD = "dashboard";
	public String FORGOT_PASSWORD = "forgotPwd";
	public String UNLOCK_ACC = "unlock";
	public String VIEW_ENQ = "view-enquiries";
	public String ENQ_TABLES = "view-tables";
	
	public String ACCOUNT_STATUS_LOCKED = "LOCKED";
	public String ACCOUNT_STATUS_UNLOCKED = "UNLOCKED";
	
	public String LOGIN_SUCCESS = "Login Success.";
	public String LOGIN_FAILURE = "Email or Password is incorrect.";
	
	
	public String SIGNUP_FAILURE = "Account already exist. Please do login.";
	public String SIGNUP_SUCCESS = "Account created, please check your mail for login details.";
	
	public String EMAIL_SUBJECT = "Please unlock your account";
	
	public String UNLOCK_ACCOUNT_FAILURE_PASSWORD = "Password and confirm password field not matched.";
	public String UNLOCK_ACCOUNT_SUCCESS = "Unlock account success.";
	
	public String FORGOT_PASSWORD_FAILURE = "Email not found, could you please do a fresh signup.";
	public String FORGOT_PASSWORD_SUCCESS = "Password send on your email, please check.";

}
