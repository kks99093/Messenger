<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/login.css">
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/login.js"></script>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

<div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">로그인</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">회원가입</label>
		<c:if test="${loginFailed eq 'username' }">
			아이디 없음
		</c:if>
		<c:if test="${loginFailed eq 'password' }">
			비밀번호 틀림
		</c:if>
		
		<div class="login-form">
			<form action="/auth/loginProc" method="post">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label">아이디</label>
						<input name="username" id="user" type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">비밀번호</label>
						<input name="password" id="pass" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<input type="submit" class="button" value="로그인">
					</div>
					<div class="hr"></div>

				</div>
			</form>
			<div class="sign-up-htm">
				<form action="/joinProc" method="post" id="joinForm">
					<div class="group">
						<label for="user" class="label">아아디</label>
						<input name="username" id="joinUsername" type="text" class="input">
						<button type="button" id="idConfirmChkBtn">중복확인</button>
					</div>
					<div class="group">
						<label for="pass" class="label">비밀번호</label>
						<input name="password" id="joinPassword" type="password" class="input" data-type="password">
						
					</div>
					<div class="group">
						<label for="pass" class="label">비밀번호 확인</label>
						<input name="passwordre" id="joinPasswordRe" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="pass" class="label">이름</label>
						<input name="name" id="name" type="text" class="input">
					</div>
					<div class="group">
						<input type="submit" class="button" value="가입하기">
					</div>
					<div class="hr"></div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>