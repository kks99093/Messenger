package com.my.messenger.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.my.messenger.model.entity.UserInfo;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	
	public PrincipalDetails(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//권한부여
		// role이 String 타입으로 돼있는데 여기서 returnb 받는건 Collection 타입이기에 일단 Collection타입을 만들어준다
		Collection<GrantedAuthority> collect = new ArrayList<>(); //ArrayList는 Collection의 자식이기때문에 ArrayList로 객체생성 가능
		collect.add(new GrantedAuthority() {  //collect에 GrantedAuthority타입을 넣어야하기에 new GrantedAuthority 새로운 객체를 만들어준다			

			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return userInfo.getRole();		//GrantedAuthority은 String타입으로 return을 하기에 여기다가 role을 넣어준다
			}
		});
		return null;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {		
		return userInfo.getName();
	}


	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
