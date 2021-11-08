<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>   
<style>
.profile_container{
	margin-left: 300px;
	margin-top : 200px;
	width: 700px;
	display: flex;
	border: solid black 1px;
}

.profileFrm{
	width: 40%;
}

.profile_img_div{
	display: flex;
    flex-direction: column;
    border-right: solid black 1px;
}

.profile_img{
	margin: 20px;
	width:105px;
	height:135px;
}

.profile_img_ul{
	list-style: none;
}

.profileImg_input{
	margin-top: 10px;
}

.profile_info_ul{
	list-style: none;
	margin: 50px;
}

.profile_info_ul li{
	font-size: 18px;
	margin: 10px;
}
.submit_btn{
	margin: 100px;
}
</style>
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

<script>
	$(document).ready(function(){
		$('#profileFrm').submit(function(){
			var profileImg = $('#profileImg').val()
			if(profileImg == null || profileImg.trim() == ''){
				alert('파일을 선택 해주세요')
				return false
			}
			
			return true;
			
		})
	})
</script>