<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/js/jquery-3.4.1.min.js"></script>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<style>
.wrtie_name{
	text-align: center;
}

.write_container{
	width: 60%;
	height: 100%;
	margin-top: 30px;
}

.write_form{
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	margin-left: 200px; 
}
.write_form input{
	margin-top : 30px;
}
.write_form textarea{
	margin-top : 10px;
}

.write_form_submit{
	width: 80px;
}
</style>
<div class="wrtie_name"><h1>${writeOrUpd}</h1></div>
<div class="write_container">
	<form action="/board/writeProc" method="post" class="write_form" id="write_form">
		<input type="text" id="title" name="title" placeholder="제목" value="${boardInfo.title == null ? '' : boardInfo.title }">
		<textarea id="content" name="content" rows="5" cols="3">${boardInfo.content == null ? '' : boardInfo.content }</textarea>
		<input class="write_form_submit" type="submit" value="${writeOrUpd}">
		<input type="hidden" name="category" value="${boardInfo.category }">
		<input type="hidden" name="boardPk" value="${boardInfo.boardPk }">
	</form>
</div>
<script>
	$(document).ready(function(){
		
		$('#write_form').submit(function(){
			var title = $('#title').val();
			var content = $('#content').val();
			if(title.trim() == ''){
				alert('제목을 입력해주세요')
				return false;
			}else if(content.trim() == ''){
				alert('내용을 입력해주세요')
				return false;
			}
			
			return true;
			
			
		})
		
		
	})
</script>