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
<script src="/js/chat.js"></script>
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
</html>