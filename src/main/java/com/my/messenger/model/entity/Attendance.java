package com.my.messenger.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendancePk;
	
//	private Integer userPk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPk")
	private UserInfo userInfo;
	
	private int year;
	
	private int month;
	
	private int day;
	
	private int startHour;
	
	private int startMinute;
	
	private int endHour;
	
	private int endMinute;
	
	private int state;			//출석, 퇴근 상태
}
