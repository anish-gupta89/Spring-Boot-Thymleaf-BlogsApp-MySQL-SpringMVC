package com.ag.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ag.AppUtils;
import com.ag.model.LoginRequestModel;
import com.ag.model.SignUpRequestModel;
import com.ag.service.UserServices;

@Controller
public class UserController {

	@Autowired
	UserServices userServices;
	
	@Autowired
	HttpSession session;
	
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		model.addAttribute("signupreq", new SignUpRequestModel());
		return AppUtils.SIGN_UP;
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("loginreq", new LoginRequestModel());
		return AppUtils.LOGIN;
	}
	
	@PostMapping(value = "/login")
	public String doLogin(LoginRequestModel request,Model model) {
		System.out.println(request);
		String response = userServices.login(request);
		if(response.equals(AppUtils.LOGIN_SUCCESS)) {
			return "redirect:"+AppUtils.USER_INDEX;
		}
		model.addAttribute("loginreq", new LoginRequestModel());
		model.addAttribute("errMsg", AppUtils.LOGIN_FAILURE);
		return AppUtils.LOGIN;
	}
	
	
	@PostMapping(value = "/signup")
	public String doSignUp(@ModelAttribute("signupreq") SignUpRequestModel request,Model model) {
		 String response = userServices.signUp(request);
		 if(response.equals(AppUtils.SIGN_UP_DUPLICATE_EMAIL_ERROR)) {
			 model.addAttribute("errMsg", AppUtils.SIGN_UP_DUPLICATE_EMAIL_ERROR);
			 
		 } else {
			 model.addAttribute("successMsg", AppUtils.SIGN_UP_SUCCESS);
		 }
		return AppUtils.SIGN_UP;
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model) {
		session.invalidate();
		return "redirect:/";
	}
	

}
