package com.my.messenger.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chatRoomPk;	//채팅방 pk
	
	@Column
	private Integer userPk;		//유저 pk
	
	@Column
	private int state;			//1대1일경우 1, 단체챗일땐 2
	
	@Column
	private int roomNumber;
}
