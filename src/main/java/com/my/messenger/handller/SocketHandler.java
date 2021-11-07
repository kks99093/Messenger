package com.my.messenger.handller;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.my.messenger.util.JsonToObjectParser;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	private JsonToObjectParser jsonToObjectParser = new JsonToObjectParser();
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 Map
									//웹소켓세션이 현재 이 채팅방에 들어와있는 사람들, sessionMap에는 현재 채팅방에 있는 사람들의 정보가 담겨있는듯
																
	//밑의 메소드들은 상속받은 TextWebSocketHandler가 상속받은 AbstractWebSocketHandler에 들어있는 메소드
	@SuppressWarnings("unchecked")
	@Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결 ( 웹소켓이 연결되면 동작 )
		super.afterConnectionEstablished(session); //채팅방에 입장할때 여기서 해당 브라우저에 소켓을 연결하면서 id를 부여
		sessionMap.put(session.getId(), session);  //여기서 id를 키값으로 session을 sessionMap에 넣어놓는다
		
		
		JSONObject obj = new JSONObject();
		obj.put("type", "getId"); 	//입장할때 보내는 메세지인지 채팅때 보내는 메세지인지를 구분하기위해 type에 getId를 넣고 js에서 구분
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString())); //채팅방에 입장할때 무슨 메세지를 보냄
		
	}
	
	
	@Override //메시지 발송 (메세지를 수신하면 실행)
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String msg = message.getPayload(); //js에서 ws.send("내용")로 보내는 내용이 여기에 담김
		JSONObject obj = jsonToObjectParser.jsonToObject(msg); // 받은내용을 json으로 바꿈
		
		for(String key : sessionMap.keySet()) {			 
			WebSocketSession wss = sessionMap.get(key);
			//채팅방에 접속한 사람들(sessionMap)중에 메세지 보낸사람의 정보를 wss에 담음
			try {
				
				wss.sendMessage(new TextMessage(obj.toJSONString()));
				//메세지 보내는사람의 정보로 메세지를 넣어서 js의 onmessage로 다시 보냄
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Override 
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료 (웹소켓이 종료되면 동작)
		sessionMap.remove(session.getId());//sessionMap에서 getId()의 키값을 가진 sessionMap을 제거하고
		super.afterConnectionClosed(session, status); //여기서 소켓을 끊는건가?
	}

}
