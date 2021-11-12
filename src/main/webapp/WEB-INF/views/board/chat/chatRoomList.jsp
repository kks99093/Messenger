<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>
<link rel="stylesheet" href="/css/chatRoomList.css"> 
<script src="/js/chatRoomList.js"></script>
<div id="notebooks" ng-app="notebooks" ng-controller="NotebookListCtrl">
  <ul id="notebook_ul">
  	<c:forEach var="chatRoom" items="${chatRoomList}">
			<li class="chatListLi" onclick="chatClick(${chatRoom.myInfo.userPk}, ${chatRoom.yourInfo.userPk})"><span class="nameSpan">${chatRoom.yourInfo.name } 님과의 대화 :</span> <br>
				<span class="chatMsgSpan">${chatRoom.chatData.chatMsg }</span>
			</li>
	</c:forEach>
  </ul>
</div>