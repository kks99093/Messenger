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

@Entity
@Data
public class Board{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardPk;
	
//	private Integer userPk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPk")
	private UserInfo userInfo;

	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private int category; 
	
	@Column
	@CreationTimestamp
	private Timestamp createTime;	
	

	

}
