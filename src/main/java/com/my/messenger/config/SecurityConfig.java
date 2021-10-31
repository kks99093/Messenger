package com.my.messenger.config;

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
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("user/**").authenticated() //"/user/**"로 들어오는 주소에는 인증이 필요함
			.antMatchers("manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // "/manager/**"로 들어오는 주소는 인증과 권한 필요
			.anyRequest().permitAll() //위의 주소를 제외하고는 전부 허용
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/")
			.failureHandler(loginFailUreHandller);
		
		http.exceptionHandling().accessDeniedPage("/"); //접근 권한이 없을시 보낼 페이지
			
	}

}
