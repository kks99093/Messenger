package com.my.messenger.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("해당 사용자를 찾을수 없습니다.");
		}
		return new PrincipalDetails(userEntity);
	}
	
	

}
