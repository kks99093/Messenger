package com.my.messenger.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.messenger.auth.PrincipalDetails;
import com.my.messenger.model.dto.ChatRoomDto;
import com.my.messenger.model.entity.ChatData;
import com.my.messenger.model.param.UserParam;
import com.my.messenger.service.ChatService;
import com.my.messenger.util.Const;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	//챗
	@GetMapping("/chat/chat")
	public String chat(Model model, UserParam userParam){
		int roomNumber = 0;
		List<ChatData> chatDataList = new ArrayList<>();
		int result = chatService.selChatRoom(userParam.getMyPk(), userParam.getYourPk());
		if(result == 0) { //대화방 없음 방 생성
			roomNumber = chatService.insChatRoom(userParam.getMyPk(), userParam.getYourPk());
		}else { //받아온 방번호로 방을 연결
			roomNumber = result;
			chatDataList = chatService.selChatDataList(roomNumber);
		}
		
		model.addAttribute("chatDataList", chatDataList);
		model.addAttribute(userParam);
		model.addAttribute("roomNumber", roomNumber);
		return "/board/chat/chat";
	}
	
	@GetMapping("/chat/chatRoomList")
	public String chatRoomList(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		List<ChatRoomDto> chatRoomList = chatService.findByUserPk(principalDetails.getUserInfo());
		for(ChatRoomDto chatRoomDto : chatRoomList) {
			System.out.println(chatRoomDto);
		}
		model.addAttribute("chatRoomList", chatRoomList);
		model.addAttribute(Const.TITLE, "채팅방 리스트");
		model.addAttribute(Const.VIEW, "/board/chat/chatRoomList");
		return Const.TEMPLATE;
	}

}
