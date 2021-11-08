package com.my.messenger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.messenger.auth.PrincipalDetails;
import com.my.messenger.model.RestFile;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.service.UserService;
import com.my.messenger.util.Const;

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
	
	@GetMapping("/user/updProfileView")
	public String updProfileView(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		model.addAttribute(Const.TITLE,"프로필 수정");
		model.addAttribute(Const.VIEW,"/user/profileView");
		return Const.TEMPLATE;
		
	}
	
	@PostMapping("/user/updProfileProc")
	public String updProfileProc(RestFile restFile, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		userService.updProfileProc(restFile, request, principalDetails.getUserInfo());
		return "redirect:/user/updProfileView";
	}
}
