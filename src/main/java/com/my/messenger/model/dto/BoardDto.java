package com.my.messenger.model.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.my.messenger.model.entity.Board;

import lombok.Data;

@Data
public class BoardDto{
	
	private Integer boardPk;
	
	private Integer userPk;

	private String title;
	
	private String content;
	
	private int category;
	
	private String username; //DTO 추가
	
	private Timestamp createTime;

}
