package com.my.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.messenger.model.entity.Attendance;
import com.my.messenger.model.entity.UserInfo;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
//	Attendance findByUserPk(int userPk);
//	Attendance findByUserPkAndYearAndMonthAndDay(int userPk, int year, int month, int day);
//	List<Attendance> findByUserPkAndYearAndMonthOrderByDayAsc(int userPk, int year, int month);
	
	Attendance findByUserInfo(UserInfo userInfo);
	Attendance findByUserInfoAndYearAndMonthAndDay(UserInfo userInfo, int year, int month, int day);
	List<Attendance> findByUserInfoAndYearAndMonthOrderByDayAsc(UserInfo userInfo, int year, int month);
	

}
