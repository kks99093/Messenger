package com.my.messenger.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class ChatData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chatDataPk;
	
	@Column
	private Integer userPk;
	
	@Column
	private int roomNumber;
	
	@Column
	private String name;
	
	@Column
	private String chatMsg;
	
	@Column
	@CreationTimestamp
	private Timestamp creatTime;
	

}
