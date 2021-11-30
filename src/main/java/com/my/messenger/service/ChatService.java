package com.my.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.messenger.model.dto.ChatRoomDto;
import com.my.messenger.model.entity.ChatData;
import com.my.messenger.model.entity.ChatRoom;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.repository.ChatDataRepository;
import com.my.messenger.repository.ChatRoomRepository;
import com.my.messenger.repository.UserRepository;

@Service
public class ChatService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ChatRoomRepository chatRoomRep;
	
	@Autowired
	private ChatDataRepository chatDataRep;
	
	@Autowired
	private UserRepository userRep;
	
	public int selChatRoom(int myPk, int yourPk) {
		int result = 0;
//		List<ChatRoom> chatRoomList = chatRoomRep.findByStateAndMyPkOrYourPk(1,myPk, yourPk);
//		if(chatRoomList.size() > 0) {
//			result = chatRoomList.get(0).getRoomNumber();
//		}
		List<ChatRoom> myChatRoomList = chatRoomRep.findByStateAndUserInfo(1, myPk);
		List<ChatRoom> yourChatRoomList = chatRoomRep.findByStateAndUserInfo(1, yourPk);
		roomNumberChk:
		for(ChatRoom myChatRoom : myChatRoomList) {
			for(ChatRoom yourChatRoom : yourChatRoomList) {
				if(myChatRoom.getRoomNumber() == yourChatRoom.getRoomNumber()) {
					result = myChatRoom.getRoomNumber();
					break roomNumberChk;
				}				
			}
		}
		
				
		return result;
	}
	
	public int insChatRoom(int myPk, int yourPk) {
		int roomNum = 0;
		ChatRoom chatRoom = chatRoomRep.findByMaxRoomNumber();
		if(chatRoom == null) {
			roomNum = makeOneChatRoom(1, myPk, yourPk);
		}else {
			roomNum = makeOneChatRoom(chatRoom.getRoomNumber()+1, myPk, yourPk);
		}		
		return roomNum;
		
		
	}
	
	public int makeOneChatRoom(int roomNumber, int myPk, int yourPk) {
		UserInfo myUserInfo = userService.selUserPk(myPk);
		UserInfo yourUserInfo = userService.selUserPk(yourPk);
		ChatRoom  myChatRoom = new ChatRoom();
		myChatRoom.setRoomNumber(roomNumber);
//		myChatRoom.setUserPk(myPk);
		myChatRoom.setUserInfo(myUserInfo);
		myChatRoom.setState(1);
		chatRoomRep.save(myChatRoom);
		ChatRoom  yourChatRoom = new ChatRoom();
		yourChatRoom.setRoomNumber(roomNumber);
//		yourChatRoom.setUserPk(yourPk);
		yourChatRoom.setUserInfo(yourUserInfo);
		yourChatRoom.setState(1);
		chatRoomRep.save(yourChatRoom);
		return roomNumber;
	}
	
	public int insChatData(JSONObject jsonOb) {
		ChatData chatData = new ChatData();
		String chatMsg = (String) jsonOb.get("msg");		
		String name = (String) jsonOb.get("name");
		String strRoomNumber = (String) jsonOb.get("roomNumber");
		int roomNumber = Integer.parseInt(strRoomNumber);
		String strUserPk = (String) jsonOb.get("myPk");
		int userPk = Integer.parseInt(strUserPk);
		UserInfo userInfo = userService.selUserPk(userPk);
		chatData.setChatMsg(chatMsg);
		chatData.setName(name);
		chatData.setRoomNumber(roomNumber);
//		chatData.setUserPk(userPk);
		chatData.setUserInfo(userInfo);
		chatData.setCategory(1);
		
		chatDataRep.save(chatData);
		
		return 0;
		
	}
	
	public int insChatDateImg(int roomNumber, String strMyPk, String fileName) {
		ChatData chatData = new ChatData();
		int myPk = Integer.parseInt(strMyPk);
		UserInfo userInfo = userRep.findByUserPk(myPk);
		chatData.setChatMsg(fileName);
		chatData.setName(userInfo.getName());
		chatData.setRoomNumber(roomNumber);
//		chatData.setUserPk(myPk);
		chatData.setUserInfo(userInfo);
		chatData.setCategory(2);
		chatDataRep.save(chatData);
		return 0;
		
		
	}
	
	public List<ChatData> selChatDataList(int roomNumber){
		List<ChatData> chatDataList = chatDataRep.findByRoomNumberOrderByCreatTimeAsc(roomNumber);
		return chatDataList;
	}
	
	public List<ChatRoomDto> findByUserPk(UserInfo userInfo){
		List<ChatRoom> chatRoomList = chatRoomRep.findByUserInfo(userInfo.getUserPk());
		List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
		for(ChatRoom chatRoom : chatRoomList) {
			ChatRoomDto chatRoomDto = new ChatRoomDto();
			int roomNumber = chatRoom.getRoomNumber();
			chatRoomDto.setRoomNumber(roomNumber);
			chatRoomDto.setMyInfo(userInfo);			
			chatRoomDto.setChatData(chatDataRep.findByRoomNumber(roomNumber));
			List<ChatRoom> chatRoomInfoList = chatRoomRep.findByRoomNumber(roomNumber);
			for(ChatRoom chatRoomInfo : chatRoomInfoList) {
				if(chatRoomInfo.getUserInfo().getUserPk() != userInfo.getUserPk()) {					
					UserInfo yourInfo = userRep.findByUserPk(chatRoomInfo.getUserInfo().getUserPk());
					if(yourInfo == null) {
						yourInfo = new UserInfo();
						yourInfo.setName("(이름없음)");
					}
					chatRoomDto.setYourInfo(yourInfo);
					chatRoomDtoList.add(chatRoomDto);
				}
			}
		}
		
		return chatRoomDtoList;
		
	}
}
