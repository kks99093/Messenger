package com.my.messenger.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import com.my.messenger.handller.SocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	//여기가 웹소켓 설정하는 곳인듯??
	//내가 만든 핸들러를 웹소켓에서 쓰겠다고 등록하는부분인듯
	@Autowired
	private SocketHandler socketHandler; //이거 import할때 내가 만들어놓은 socketHandler를 import 해야함
		

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler, "/chating/{roomNumber}");
		// /chating로 들어오는 주소를 socketHandler와 연결시켜주는듯
		
	}
	
	
	//파일 크기
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(5000000);
		container.setMaxBinaryMessageBufferSize(5000000);
		return container;
	}
	

}
