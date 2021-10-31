package com.my.messenger.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.messenger.auth.PrincipalDetails;

@Controller
public class TestController {


	
	@GetMapping({"/temp"})
	public String temp() {
		return "/template/mainTemp";
	}
	
}
