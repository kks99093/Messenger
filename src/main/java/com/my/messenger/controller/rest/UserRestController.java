package com.my.messenger.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.messenger.auth.PrincipalDetails;
import com.my.messenger.model.entity.Attendance;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.model.param.UserParam;
import com.my.messenger.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/idConfirmChk")
	public int idConfirmChk(@RequestBody UserParam userParam) {
		int result = userService.idConfirmChk(userParam);
		return result;
	}
	
	@PostMapping("/auth/attendance")
	public int attendance(@RequestBody Attendance attendance, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Integer userPk = principalDetails.getUserInfo().getUserPk();
		attendance.setUserPk(userPk);
		userService.insAttendance(attendance);
		return 0;
	}
	
	

}
