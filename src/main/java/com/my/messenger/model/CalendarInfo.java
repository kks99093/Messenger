package com.my.messenger.model;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class CalendarInfo {
	
	private List<Integer> daysList;		//일수
	
	private int year;					//년
	
	private int month;					// 월
	
	private int day;					//일
	
	private int remine;					//뒤에 빈공간 갯수
}
