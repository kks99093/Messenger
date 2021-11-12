<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>
<script src="/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/css/team.css"> 
<script src="/js/team.js"></script>
<div class="board_boardName"> <h1>${boardName }</h1></div>
<div class="board_container">
<input type="hidden" id="boardCategory" value=${boardInfo.category }>	
	<!-- 여기가 게시판 -->
	<div class="board">
		<table class="board_table">
			<tr>
				<td class="board_no">No</td>
				<td class="board_title">제목</td>
				<td class="board_username">작성자</td>
				<td class="board_date">작성 날짜</td>
			</tr>
			<c:forEach var="boardDto" items="${boardDtoList.content }">
				<tr onclick="moveDetail(${boardDto.boardPk})">
					<td class="board_no">${boardDto.boardPk }</td>
					<td class="board_title">${boardDto.title }</td>
					<td class="board_username">${boardDto.name }</td>
					<td class="board_date">${boardDto.createTime }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="board_write_btn_div">
			<button type="button" id="board_write_btn" class="board_write_btn">글쓰기</button>
		</div>
		<div class="page">
			<ul class="page_ul">
				<li class="page_li page_prev ${boardDtoList.pageable.pageNumber == 0 ? 'disable_evt disable_cursor' : '' }" onclick="pageMove(${boardDtoList.pageable.pageNumber-1})" >이전페이지</li>
				<c:forEach begin="${startIdx}" end="${endIdx }" varStatus="status">
					<li class="page_li page_num ${boardDtoList.pageable.pageNumber == status.index ? 'disable_evt disable_cursor enable_pageNum' : '' }" onclick="pageMove(${status.index})" >${status.index+1 }</li>
				</c:forEach>				
				<li class="page_li page_next  ${boardDtoList.pageable.pageNumber == endPage ? 'disable_evt disable_cursor' : '' }" onclick="pageMove(${boardDtoList.pageable.pageNumber+1})" > 다음페이지</li>
			</ul>
		</div>
	</div>
	
	
	<!-- 여기가 유저목록 -->
	<c:if test="${boardInfo.category != 4 }">
		<div class="user_list">
			<input type="hidden" id="myPk"value="${principal.userInfo.userPk}">
			<ul>
			<c:forEach var="userInfo" items="${userDtoList }">
				<li class=" ${userInfo.role eq '팀장' ? 'teamReader' : '' } ${userInfo.userPk == principal.userInfo.userPk ? 'myInfo' : 'mouse_hover mouse_cursor'}" onclick="userChatClick(${userInfo.userPk })">
					<span>${userInfo.name }</span>
				</li>	
			</c:forEach>
			</ul>
		</div>
	</c:if>
</div>