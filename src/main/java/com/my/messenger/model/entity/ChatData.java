package com.my.messenger.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class ChatData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chatDataPk;
	
//	@Column
//	private Integer userPk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPk")
	private UserInfo userInfo;
	
	@Column
	private int roomNumber;
	
	@Column
	private String name;
	
	@Column
	private String chatMsg;
	
	@Column
	private int category; //1=텍스트 , 2=이미지
	
	@Column
	@CreationTimestamp
	private Timestamp creatTime;
	

}
