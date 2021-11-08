package com.my.messenger.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.messenger.auth.PrincipalDetails;
import com.my.messenger.model.CalendarInfo;
import com.my.messenger.model.dto.BoardDto;
import com.my.messenger.model.entity.Attendance;
import com.my.messenger.model.entity.Board;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.service.BoardService;
import com.my.messenger.service.UserService;
import com.my.messenger.util.Const;

@Controller
public class BoardController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/","/board/main"})
	public String index(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, 
			CalendarInfo calendarInfo) {
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
				if(endTime.equals("00 : 00")) {
					endTime = null;
				}
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
	
	//게시판
	@GetMapping({"/board/team"})
	public String team(Model model, Board board, 
			@PageableDefault(sort = {"createTime"}, direction = Direction.DESC, size = 10) Pageable pageable) {
		
		if(board == null || board.getCategory() == 0) {
			return "redirect:/"; //카테고리 없이 접근하면 index페이지로 보냄
		}
		String boardName = "";
		
		switch(board.getCategory()) {
			case 1 :
				boardName = "A 게시판";
				break;
			case 2 :
				boardName = "B 게시판";
				break;
			case 3 :
				boardName = "C 게시판";
				break;
			case 4 :
				boardName = "공지 사항";
				break;
		}
		 
		
		//페이징
		Page<BoardDto> boardDtoList = boardService.selBoardList(board.getCategory(), pageable);
		int startIdx = (boardDtoList.getPageable().getPageNumber()/10) * 10;		
		int endIdx = boardDtoList.getTotalPages() > startIdx+9 ? startIdx+9 : boardDtoList.getTotalPages()-1;
		if(endIdx == -1) {
			endIdx = 0;
		}
		int endPage = boardDtoList.getTotalPages()-1;
		if(endPage <0) {
			endPage = 0;
		}
		model.addAttribute("endIdx", endIdx);
		model.addAttribute("startIdx", startIdx);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boardDtoList", boardDtoList);
		model.addAttribute("boardInfo", board);
		model.addAttribute("boardName", boardName);
		model.addAttribute(Const.TITLE, "게시판");
		model.addAttribute(Const.VIEW,"/board/team");
		
		return Const.TEMPLATE;
	}
	
	@GetMapping({"/board/writeView"})
	public String writeView(Model model, Board board) {
		if(board.getBoardPk() == null) {
			model.addAttribute("writeOrUpd", "쓰기");
			model.addAttribute("boardInfo", board);
			model.addAttribute(Const.TITLE, "글쓰기");
		}else {
			BoardDto boardInfo = boardService.selBoard(board.getBoardPk());
			model.addAttribute("writeOrUpd", "수정");
			model.addAttribute("boardInfo", boardInfo);
			model.addAttribute(Const.TITLE, "글수정");
		}		
		model.addAttribute(Const.VIEW,"/board/write");
		return Const.TEMPLATE;
	}
	
	@PostMapping({"/board/writeProc"})
	public String writeProc(Model model, Board board, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(board.getBoardPk() == null) { //글쓰기
			UserInfo userInfo = principalDetails.getUserInfo();
			board.setUserPk(userInfo.getUserPk());
			boardService.insBoard(board);
		}else { //글수정
			boardService.insBoard(board);
		}
			
		return "redirect:/board/team?category="+board.getCategory();
	}
	
	@GetMapping({"/board/boardDetail"})
	public String boardDetail(Model model, Board board) {
		BoardDto boardDto = boardService.selBoard(board.getBoardPk());
		
		model.addAttribute("boardDto", boardDto);
		model.addAttribute(Const.TITLE, "상세");
		model.addAttribute(Const.VIEW,"/board/boardDetail");
		return Const.TEMPLATE;
	}
	
	@GetMapping({"/board/delBoard"})
	public String delBoard(Board board) {
		boardService.delBoard(board);
		
		return "redirect:/board/team?category="+board.getCategory();
	}

	
	
	

}
