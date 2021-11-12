package com.my.messenger.model.dto;

import com.my.messenger.model.entity.ChatData;
import com.my.messenger.model.entity.ChatRoom;
import com.my.messenger.model.entity.UserInfo;

import lombok.Data;

@Data
public class ChatRoomDto extends ChatRoom{
	
	private UserInfo myInfo;
	
	private UserInfo yourInfo;
	
	private ChatData chatData;

}
