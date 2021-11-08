<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>         
<script src="/js/jquery-3.4.1.min.js"></script>    
<link rel="stylesheet" href="/css/boardDetail.css"> 
<script src="/js/boardDetail.js"></script>
<input type="hidden" id="category" value="${boardDto.category }">
<div class="board_boardName"> <h1>게시판 이름</h1></div>
<div class="board_container">
	<div class="boardDetail">
		<div class="detail_top">
			<div class="detail_no detail_border">${boardDto.boardPk }</div>
			<div class="detail_title detail_border">${boardDto.title }</div>
		</div>
		<div class="detail_userInfo">
			<div class="detail_username detail_border">작성자 : ${boardDto.name }</div>
			<div class="detail_date detail_border">작성 시간 : ${boardDto.createTime }</div>
		</div>
		
		<sec:authorize access="isAuthenticated()">
			<div class="detail_content detail_border">${boardDto.content }</div>
		</sec:authorize>
		<c:if test="${boardDto.userPk == principal.userInfo.userPk}">
			<div class="detail_upd_del">		
				<input type="hidden" id="boardPk" value="${boardDto.boardPk }" >
				<button type="button" id="upd_btn">수정</button>
				<button type="button" id="del_btn">삭제</button>
			</div>
		</c:if>
	</div>
	
	
	<!-- 여기가 유저목록 -->
	<c:if test="${boardInfo.category != 4 }">
		<div class="user_list">
			aaaaaaaa
		</div>
	</c:if>
	
</div>
