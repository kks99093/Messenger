package com.my.messenger.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.messenger.model.RestFile;
import com.my.messenger.model.dto.UserDto;
import com.my.messenger.model.entity.Attendance;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.model.param.UserParam;
import com.my.messenger.repository.AttendanceRepository;
import com.my.messenger.repository.UserRepository;
import com.my.messenger.util.FileUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//로그인 실패시 아이디, 비밀번호 확인
	public String loginFail(String username) {
		if(userRepository.findByUsername(username) == null) {
			return "username";
		}else {
			return "password";
		}
	}
	
	//아이디 중복확인
	public int idConfirmChk(UserParam userParam) {		
		if(userRepository.findByUsername(userParam.getUsername()) == null) {
			return 0; //사용가능
		}else if(userRepository.findByUsername(userParam.getUsername()) != null) {
			return 1; //사용불가
		}else {
			return -1; //DB오류
		}
	}
	
	//회원가입
	public int join(UserInfo userInfo) {
		String beforePassword = userInfo.getPassword();
		String afterPassword = bcrypt.encode(beforePassword);
		userInfo.setPassword(afterPassword);
		UserInfo result = userRepository.save(userInfo);
		if(result == null) {
			return -1;
		}else {
			return 1;
		}
	}
	
	
	//출석 Insert
	public void insAttendance(Attendance attendance) {
		if(attendance.getState() == 0) {
			attendance.setState(1);
			attendanceRepository.save(attendance);
		}else if(attendance.getState() == 1) {
			Attendance attendanceDto = attendanceRepository.findByUserPkAndYearAndMonthAndDay(attendance.getUserPk(), attendance.getYear(), attendance.getMonth(), attendance.getDay());
			attendanceDto.setState(2);
			attendanceDto.setEndHour(attendance.getEndHour());
			attendanceDto.setEndMinute(attendance.getEndMinute());
			attendanceRepository.save(attendanceDto);
		}

	}
	
	//출석 체크
	public int attendanceChk(Attendance attendance) {
		Attendance attendanceDto = attendanceRepository.findByUserPkAndYearAndMonthAndDay(attendance.getUserPk(), attendance.getYear(), attendance.getMonth(), attendance.getDay());
		if(attendanceDto == null) {
			return 0; //출근X
		}else if(attendanceDto.getState() == 1) {
			return 1; //출근O
		}else {
			return 2; //퇴근O
		}
	}
	
	//달 출석 현황
	public List<Attendance> attYMList(int userPk, int year, int month){		
		List<Attendance> attYMList = attendanceRepository.findByUserPkAndYearAndMonthOrderByDayAsc(userPk, year, month);
		return attYMList;
		
	}
	
	//프로필 이미지
	public void updProfileProc(RestFile restFile, HttpServletRequest request, UserInfo userInfo) {
		String rPath = request.getServletContext().getRealPath("/");
		String path = rPath+"upload/profileImg/";
		System.out.println(path);
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		UserInfo userEntity = userRepository.findByUserPk(userInfo.getUserPk());
		if(userInfo.getProfileImg() != null) {
			int result = FileUtils.deleteFile(path, userEntity.getProfileImg());
		}
		String saveFileNm = FileUtils.createFile(path, restFile);
		userEntity.setProfileImg(saveFileNm);
		userRepository.save(userEntity);
		
	}
	
	public List<UserDto> selUserList(int departMent){
		List<UserInfo> userInfoList = userRepository.findByDepartMentOrderByRoleDesc(departMent);
		List<UserDto> userDtoList = new ArrayList<>();
		for(UserInfo userInfo : userInfoList) {
			UserDto userDto = modelMapper.map(userInfo, UserDto.class);
			userDtoList.add(userDto);
		}
		return userDtoList;
		
	}

}
