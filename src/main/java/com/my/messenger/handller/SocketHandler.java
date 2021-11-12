package com.my.messenger.handller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.my.messenger.service.ChatService;
import com.my.messenger.util.JsonToObjectParser;

@Component
public class SocketHandler extends TextWebSocketHandler {

	@Autowired
	private ChatService chatService;
	
	private JsonToObjectParser jsonToObjectParser = new JsonToObjectParser();
	
	
	List<HashMap<String,Object>> rls = new ArrayList<>(); //채팅방 세션
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결될때 동작
		super.afterConnectionEstablished(session);
		String url = session.getUri().toString();
		String roomNum = url.split("/chating/")[1];
		int roomNumber = 0;
		boolean flag = false;
		//DB에서 방번호를 가져옴
		String myPk = roomNum.split("&")[0];
		int intMyPk = Integer.parseInt(myPk);
		String yourPk = roomNum.split("&")[1];
		int intyourPk = Integer.parseInt(yourPk);
		if(!roomNum.equals("0")) { //1대1방일경우			
			int result = chatService.selChatRoom(intMyPk, intyourPk);
			if(result == 0) { //대화방 없음 방 생성
				roomNumber = chatService.insChatRoom(intMyPk, intyourPk);
			}else { //받아온 방번호로 방을 연결
				roomNumber = result;
			}
		}else {	//단체방인경우
			
		}
		
		int idx = rls.size();
		if(rls.size() >0) {
			for(int i=0; i<rls.size(); i++) {
				int intRN = (int) rls.get(i).get("roomNumber");
				if(intRN == roomNumber) {
					flag = true;
					idx = i;
					break;
				}
			}
		}
		
		if(flag) {
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		}else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}

	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser.jsonToObject(msg);
		int insResult = chatService.insChatData(obj);
		String rN = (String) obj.get("roomNumber");		
		int intRN = Integer.parseInt(rN);
		HashMap<String, Object> temp = new HashMap<String, Object>();
		
		String msgType = (String) obj.get("type");
		
		if(rls.size() > 0) {
			for(int i=0; i<rls.size(); i++) {
				int roomNumber = (int) rls.get(i).get("roomNumber");
				if(roomNumber == intRN) {
					temp = rls.get(i);
					
					break;
				}
			}
		}
		
		if(!msgType.equals("fileUpload")) {
			for(String k : temp.keySet()) {
				if(k.equals("roomNumber")) {
					continue;
				}
				WebSocketSession wss = (WebSocketSession) temp.get(k);
				if(wss != null) {
					try {
						wss.sendMessage(new TextMessage(obj.toJSONString()));
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.err.println("소켓 Close");
		
		if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for(int i=0; i<rls.size(); i++) {
				rls.get(i).remove(session.getId());
			}
		}
		super.afterConnectionClosed(session, status);
	}
	

}
