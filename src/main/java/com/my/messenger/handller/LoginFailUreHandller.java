package com.my.messenger.handller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.RestController;

import com.my.messenger.service.UserService;

@RestController
public class LoginFailUreHandller implements AuthenticationFailureHandler{
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//로그인 실패시 아이디가 있는지 확인후 없으면 아이디없음, 있으면 비밀번호 틀림
		String result = userService.loginFail(request.getParameter("username"));
		
		//세션에 result를 담아서 login페이지로 보냄
		request.setAttribute("loginFailed", result);
		request.getRequestDispatcher("/login").forward(request, response);
		
		
		
	}

}
