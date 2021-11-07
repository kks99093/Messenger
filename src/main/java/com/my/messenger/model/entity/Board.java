package com.my.messenger.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Board{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardPk;
	
	private Integer userPk;

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
