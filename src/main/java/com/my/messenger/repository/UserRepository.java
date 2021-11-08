package com.my.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.messenger.model.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	
	public UserInfo findByUsername(String username);

	public UserInfo findByUserPk(int UserPk);
	
	public UserInfo findByUsernameAndPassword(String username, String password);
	
}
