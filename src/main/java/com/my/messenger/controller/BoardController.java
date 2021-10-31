package com.my.messenger.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.messenger.auth.PrincipalDetails;
import com.my.messenger.model.CalendarInfo;
import com.my.messenger.model.entity.Attendance;
import com.my.messenger.service.UserService;
import com.my.messenger.util.Const;

@Controller
public class BoardController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"","/","/main"})
	public String index(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, CalendarInfo calendarInfo ) {
		if(principalDetails == null) {
			return "redirect:/login";
		}else {
			
			//달력만들기
			int tempYear = 0;
			int tempMonth = 0;
			int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31 , 30, 31};
			List<Integer> days = new ArrayList<Integer>();
			
			Calendar calender = Calendar.getInstance();			
			
			if(calendarInfo.getMonth() == 0 ) { //현재
				tempYear = calender.get(Calendar.YEAR);
				tempMonth = calender.get(Calendar.MONTH)+1;
			}else {
				tempYear = calendarInfo.getYear();
				tempMonth = calendarInfo.getMonth();
			}
			//윤달 계산
			
			if((0==(tempYear%4)&& 0!=(tempYear%100))|| 0 == tempYear%400){
				monthDays[1] = 29;
			}else {
				monthDays[1] = 28;
			}
						
			calender.set(tempYear, tempMonth-1, 1); 
			int firstDayOfWeek = calender.get(Calendar.DAY_OF_WEEK)-1; //(일요일은 0, 토요일은 6)
			for(int i=0; i<firstDayOfWeek; i++) {
				days.add(i, null);
			}
			for(int j=1; j<=monthDays[tempMonth-1]; j++) {
				days.add(firstDayOfWeek, j);
				firstDayOfWeek++;
			}
			int remine = 7-days.size()%7;
			calendarInfo.setRemine(remine);
			calendarInfo.setDaysList(days);
			calendarInfo.setYear(tempYear);
			calendarInfo.setMonth(tempMonth);
			model.addAttribute("calendarInfo", calendarInfo);
			//ㅡㅡㅡㅡㅡㅡㅡㅡ
			
			//출근날짜 퇴근날짜 가져오기
			Map<Integer, String> startAttList = new HashMap<Integer, String>();
			Map<Integer, String> endAttList = new HashMap<Integer, String>();
			
			List<Attendance> attList = userService.attYMList(principalDetails.getUserInfo().getUserPk(), tempYear, tempMonth);
			for(Attendance attListFor : attList) {
				String startHour = "";
				String startMin = "";
				String endHour = "";
				String endMin = "";
				//00 : 00 으로 나타내기
				if(attListFor.getStartHour() < 10) {
					startHour = "0"+attListFor.getStartHour();
				}else {
					startHour = ""+attListFor.getStartHour();
				}
				if(attListFor.getStartMinute() < 10) {
					startMin = "0"+attListFor.getStartMinute();
				}else {
					startMin = ""+attListFor.getStartMinute();
				}
				if(attListFor.getEndHour() < 10) {
					endHour = "0"+attListFor.getEndHour();
				}else {
					endHour = ""+attListFor.getEndHour();
				}
				if(attListFor.getEndMinute() < 10) {
					endMin = "0"+attListFor.getEndMinute();
				}else {
					endMin = ""+attListFor.getEndMinute();
				}
				//ㅡㅡㅡ
				String startTime = startHour + " : " + startMin;
				String endTime = endHour + " : " + endMin;
				startAttList.put(attListFor.getDay(),startTime);
				endAttList.put(attListFor.getDay(),endTime);
			}
			
			model.addAttribute("startAttList",startAttList);
			model.addAttribute("endAttList",endAttList);
			
			
			
			//오늘 출근 퇴근 상태확인
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			String currentDate = date.format(today);
			String[] dateStrArr = currentDate.split("/");
			int currentYear = Integer.parseInt(dateStrArr[0]);
			int currentMonth = Integer.parseInt(dateStrArr[1]);
			int currentDay = Integer.parseInt(dateStrArr[2]);
			Attendance attendance = new Attendance();
			attendance.setYear(currentYear);
			attendance.setMonth(currentMonth);
			attendance.setDay(currentDay);
			attendance.setUserPk(principalDetails.getUserInfo().getUserPk());
			int attendanceChk = userService.attendanceChk(attendance);
			model.addAttribute("attendanceChk", attendanceChk);			
			//ㅡㅡㅡㅡㅡㅡㅡㅡ
			
			model.addAttribute(Const.TITLE, "메인페이지");
			model.addAttribute(Const.VIEW, "/board/main");
			
			
			
			return Const.TEMPLATE;
		}
		
	}
	
	@GetMapping({"/login"})
	public String login() {
		return "/user/login";
	}
	
	//로그인 실패했을대의 요청은 post로 날아오기때문에 로그인 실패 처리를 위해서 postMapping을 만들어놔야함
	@PostMapping({"/login"})
	public String postLogin() {
		return "/user/login";
	}
	
	
	

}
