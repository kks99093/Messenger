package com.my.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.messenger.model.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
	
	@Query(nativeQuery = true, value=" SELECT * FROM chatroom WHERE state = ?1 AND (userPk=?2 OR userPk=?3) ")
	List<ChatRoom> findByStateAndMyPkOrYourPk(int state, int myPk, int yourPk);
	
	@Query(nativeQuery = true, value=" SELECT chatRoomPk, MAX(roomNumber) as roomNumber, userPk, state FROM chatroom ")
	ChatRoom findByMaxRoomNumber();
	
	List<ChatRoom> findByStateAndUserPk(int state, int userPk);
	
	List<ChatRoom> findByUserPk(int userPk);
	
	List<ChatRoom> findByRoomNumber(int roomNumber);

}
