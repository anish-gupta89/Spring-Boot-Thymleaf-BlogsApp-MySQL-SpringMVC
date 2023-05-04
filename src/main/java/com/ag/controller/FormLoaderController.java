package com.ag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ag.AppUtils;

@Controller
public class FormLoaderController {

	@GetMapping(value = "/")
	public String index() {
		return AppUtils.INDEX;
	}
}
