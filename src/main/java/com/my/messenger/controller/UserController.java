package com.my.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/joinProc")
	public String joinProc(UserInfo userInfo) {
		int result = userService.join(userInfo);
		if(result == -1) {
			//회원가입 실패
			System.out.println("회원가입 실패");
		}else{
			System.out.println("회원가입 성공");
		}
		return "redirect:/login";
	}
}
