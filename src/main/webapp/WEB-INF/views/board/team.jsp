<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
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

.board{
	margin-left: 200px;
	flex-grow: 8.5;
	height: 100%;
	text-align: center;
}

.user_list{
	flex-grow: 1.5;
	margin-right: 100px;
	border: solid black 1px;
	text-align: center;
}
.board_table{
	width: 100%;
}
.board_table tr td{
	border: solid black 1px;
}
.board_no{
	width: 5%;
}

.board_title{
	width: 60%;
}

.board_username{
	width: 8%;
}
.board_date{
	width: 10%
}

.board_write_btn_div{
	text-align: left;
}

</style>
<div class="board_boardName"> <h1>게시판 이름</h1></div>
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
			<c:forEach var="boardDto" items="${boardDtoList }">
				<tr onclick="moveDetail(${boardDto.boardPk})">
					<td class="board_no">${boardDto.boardPk }</td>
					<td class="board_title">${boardDto.title }</td>
					<td class="board_username">${boardDto.username }</td>
					<td class="board_date">${boardDto.createTime }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="board_write_btn_div">
			<button type="button" id="board_write_btn" class="board_write_btn">글쓰기</button>
		</div>
	</div>
	
	
	<!-- 여기가 유저목록 -->
	<div class="user_list">
	aaaaaaaa
	</div>
</div>
<script>
	$(document).ready(function(){
		var boardCategory = $('#boardCategory').val();
		//글쓰기 버튼
		$('#board_write_btn').click(function(){
			location.href = '/board/writeView?category='+boardCategory;
		})
		
	})
	
	
	function moveDetail(boardPk){
		location.href = '/board/boardDetail?boardPk='+boardPk	
	}
</script>