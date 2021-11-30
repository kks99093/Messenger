<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>   
<link rel="stylesheet" href="/css/profileView.css"> 
<script src="/js/profileView.js"></script>
<div class="profile_container">
	<form action="/user/updProfileProc" method="post" id="profileFrm"  class="profileFrm" enctype="multipart/form-data">
		<div class="profile_img_div">
			<c:choose>
				<c:when test="${principal.userInfo.profileImg != null }">
					<img class="profile_img" src="/upload/profileImg/${principal.userInfo.profileImg}">
				</c:when>
				<c:otherwise>
					<img class="profile_img" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg" alt="User picture">
				</c:otherwise>
			</c:choose>
			<ul class="profile_img_ul">
				<li><input type="file" name="profileImg" id="profileImg" class="profileImg_input"></li>
				<li><span class="submit_btn"><button>수정</button></span></li>			
			</ul>
		</div>
	</form>
		<div class="profile_info">
			<ul class="profile_info_ul">
				<li><span>아이디 : ${principal.userInfo.username}</span></li>
				<li><span>이름 : ${principal.userInfo.name}</span></li>
				<li><span>직책 : ${principal.userInfo.role}</span></li>
			</ul>
		</div>
	
</div>
