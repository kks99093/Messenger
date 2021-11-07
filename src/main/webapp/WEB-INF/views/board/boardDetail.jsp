<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>         
<script src="/js/jquery-3.4.1.min.js"></script>    
<style>

.board_boardName{
	text-align: center;
}

.board_container{
	display:flex;
	width: 100%;
	height: 100%;
	margin-top: 30px;
}

.boardDetail{
	margin-left: 200px;
	flex-grow: 8.5;
	height: 100%;
	text-align: center;
}
.detail_border{
	border: solid black 1px;
}

.detail_top{
	margin: 10px;
	display: flex;
	text-align: center;
}

.detail_no{
	flex-grow: 1;
}

.detail_title{
	flex-grow: 9;
}

.detail_userInfo{
	margin: 10px;
	display: flex;
	text-align: center;
}

.detail_username{
	flex-grow: 3;
}

.detail_date{
	flex-grow: 7;
}

.detail_content{
	margin: 10px;
	height: 500px;
}
.detail_upd_del{
	display: flex;
	border: none;
}

#upd_btn{
	margin: 10px;
	text-align: center;
}

#del_btn{
	margin: 10px;
	text-align: center;
}

.user_list{
	flex-grow: 1.5;
	margin-right: 100px;
	border: solid black 1px;
	text-align: center;
}

</style>
<input type="hidden" id="category" value="${boardDto.category }">
<div class="board_boardName"> <h1>게시판 이름</h1></div>
<div class="board_container">
	<div class="boardDetail">
		<div class="detail_top">
			<div class="detail_no detail_border">${boardDto.boardPk }</div>
			<div class="detail_title detail_border">${boardDto.title }</div>
		</div>
		<div class="detail_userInfo">
			<div class="detail_username detail_border">작성자 : ${boardDto.username }</div>
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
	<div class="user_list">
	aaaaaaaa
	</div>
</div>
<script>
	$(document).ready(function(){
		var boardPk = $('#boardPk').val();
		var category = $('#category').val();
		$('#upd_btn').click(function(){
			location.href = '/board/writeView?boardPk='+boardPk;
		})
		
		$('#del_btn').click(function(){
			if(confirm('정말 삭제하시겠습니까?')){
				location.href = '/board/delBoard?boardPk='+boardPk+'&category='+category;
			}
			return	
			
		})
		
	})
	
	
	function moveDetail(boardPk){
		location.href = '/board/boardDetail?boardPk='+boardPk	
	}
</script>