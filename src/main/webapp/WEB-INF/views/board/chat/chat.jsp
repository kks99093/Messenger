<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/css/chat.css"> 
<title>채팅방</title>
</head>
<body>
<input type="hidden" id="myPk" value="${userParam.myPk }">
<input type="hidden" id="yourPk" value="${userParam.yourPk }">
<input type="hidden" id="roomNumber" value="${roomNumber }">
<input type="hidden" id="name" value="${principal.userInfo.name}">
	<div id="container" class="container">
		<h1>채팅</h1>
		<div id="chating" class="chating">
			<c:forEach var="chatData" items="${chatDataList }">
				<c:choose>
					<c:when test="${chatData.userPk == principal.userInfo.userPk }">	
						<c:choose>
							<c:when test="${chatData.category == 1 }">
								<p class="me">나 : ${chatData.chatMsg }</p>
							</c:when>
							<c:otherwise>
								<div class='img me'>나 : <img class='msgImg' src="/upload/chat/${chatData.chatMsg}"></div><div class='clearBoth'></div>
							</c:otherwise>
						</c:choose>				
						
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${chatData.category == 1}">
								<p class="others">${chatData.name} : ${chatData.chatMsg }</p>
							</c:when>
							<c:otherwise>
								<div class='img others'> ${chatData.name } : <img class='msgImg' src="/upload/chat/${chatData.chatMsg}"></div><div class='clearBoth'></div>
							</c:otherwise>
						</c:choose>						
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<p></p>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
				</tr>
				<tr>
					<th>파일업로드</th>
					<th><input type="file" id="fileUpload"></th>
					<th><button onclick="fileSend()" id="sendFileBtn">파일올리기</button></th>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
var ws;
var url;

function wsOpen(){
	var myPk = $('#myPk').val();
	var yourPk = $('#yourPk').val();	
	if(myPk != null || myPk != ''){
		ws = new WebSocket("ws://" + location.host + "/chating/"+myPk+"&"+yourPk);
	}else{
		ws = new WebSocket("ws://" + location.host + "/chating/0");	
	}		
	wsEvt();
}

function wsEvt() {
	ws.onopen = function(data){
		//소켓이 열리면 동작
	}
	
	ws.onmessage = function(data) {
		//메세지를 받으면 동작
		var msg = data.data;
		if(msg != null && msg.type != ''){
			
			var msgData = JSON.parse(msg);
			
			if(msgData.type == "message"){
				if(msgData.myPk == $('#myPk').val()){
					$("#chating").append("<p class='me'>나 : " + msgData.msg + "</p>")		
				}else{
					$("#chating").append("<p class='others'>" + msgData.name + " : " + msgData.msg + "</p>")
				}				
			}else if(msgData.type =="fileUpload"){
				if(msgData.myPk == $('#myPk').val()){
					$("#chating").append("<div class='img me'>나 : <img class='msgImg' src="+url+"></div><div class='clearBoth'></div>")
				}else{
					$("#chating").append("<div class='img others'>"+msgData.name+" : <img class='msgImg' src="+url+"></div><div class='clearBoth'></div>")	
				}	
			}else{
				console.wrn("unknown type!")
			}
				
		}else{
			url = URL.createObjectURL(new Blob([msg]));
		}
		
	}

	document.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ //enter press
			send();
		}
	});
}

//텍스트 메세지 보내기
function send() {	
	var data = {
			type : "message",
			roomNumber: $("#roomNumber").val(),
			myPk : $("#myPk").val(),
			name : $("#name").val(),
			msg : $("#chatting").val()
	}
	ws.send(JSON.stringify(data)); //여기서 보내는게 SocketHandller에 message.getPayload에 담김
	$('#chatting').val("");
}

wsOpen()
function fileSend(){
	var file = document.querySelector("#fileUpload").files[0];
	var fileReader = new FileReader();
	fileReader.onload = function(){
		var data = {
				type : "fileUpload",
				file : file,
				roomNumber : $('#roomNumber').val(),
				myPk : $("#myPk").val(),
				name : $("#name").val(),
				msg : $("#chatting").val()
		}
		arrayBuffer = this.result;
		ws.send(arrayBuffer)
		ws.send(JSON.stringify(data));
	}
	fileReader.readAsArrayBuffer(file)
	
}	
</script>
</html>