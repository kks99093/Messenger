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
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userPk;			//pk
	
	@Column(unique = true)
	private String username;		//아이디
	
	@Column
	private String password;		//비밀번호
	
	@Column
	private String name;			//이름
	
	@Column
	private String role;			//권한
	
	@Column
	@CreationTimestamp
	private Timestamp creatTime;	//가입일짜
	
}
