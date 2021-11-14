package com.my.messenger.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.my.messenger.handller.LoginFailUreHandller;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginFailUreHandller loginFailUreHandller;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() { // Entity <--> DTO 변환해주는 라이브러리
		return new ModelMapper();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/user/**","/board/**","/chat/**").authenticated()
			.antMatchers("manager/**").access("hasRole('팀장')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/")
			.failureHandler(loginFailUreHandller);
		
		http.exceptionHandling().accessDeniedPage("/"); //접근 권한이 없을시 보낼 페이지
			
	}

}
