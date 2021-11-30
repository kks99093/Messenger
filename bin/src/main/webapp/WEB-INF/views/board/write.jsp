<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/js/jquery-3.4.1.min.js"></script>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<link rel="stylesheet" href="/css/write.css"> 
<script src="/js/write.js"></script>
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
