package com.my.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.messenger.model.entity.ChatData;

public interface ChatDataRepository extends JpaRepository<ChatData, Integer>{
	
	List<ChatData> findByRoomNumberOrderByCreatTimeAsc(int roomNumber);
	
	@Query(nativeQuery = true, value = " SELECT * FROM chatdata WHERE roomNumber = ?1 ORDER BY creatTime desc LIMIT 1 ")
	ChatData findByRoomNumber(int roomNumber);
	

}
