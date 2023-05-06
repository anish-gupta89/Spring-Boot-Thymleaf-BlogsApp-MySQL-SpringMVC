package com.ag.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ag.AppConst;
import com.ag.model.LoginForm;
import com.ag.model.SignUpForm;
import com.ag.model.UnlockForm;

@Controller
public class IndexController {

	
	@Value("${spring.application.serverURL}")
	String urlForUnlockAccount;
	
	@GetMapping("/")
	public String indexLoader() {
		System.out.println(urlForUnlockAccount);
		return AppConst.INDEX;
	}

	@GetMapping("/login")
	public String loginLoader(Model model) {
		model.addAttribute("logindata", new LoginForm());
		return AppConst.LOGIN;
	}

	@GetMapping("/signup")
	public String signupLoader(Model model) {
		model.addAttribute("signupdata", new SignUpForm());
		return AppConst.SIGN_UP;
	}

	
	@GetMapping("/unlock")
	public String unlockLoader(@RequestParam String email,  Model model) {
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);
		model.addAttribute("unlockacc", unlockForm);
		return AppConst.UNLOCK_ACC;
	}
	
	@GetMapping("/forgot")
	public String forgotPasswordLoader(Model model) {
		model.addAttribute("logindata", new LoginForm());
		return AppConst.FORGOT_PASSWORD;
	}
	
}
