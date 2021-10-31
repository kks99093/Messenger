<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/login.css">
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/join.js"></script>
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
					<div class="foot-lnk">
						<a href="#forgot">Forgot Password?</a>
					</div>
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
<script>
$('document').ready(function(){
	
	var idConfirmChk = false;
	
	$('#idConfirmChkBtn').click(function(){
		var joinUsername = $('#joinUsername').val();
		
		if(idRegularExpression(joinUsername)){
			alert('아이디는 영어 + 숫자 6글자이상 입력해주세요')
			return;
		}
	
		var data = {
				username : joinUsername
		}
		
		$.ajax({
			type : "POST",
			url: "/auth/idConfirmChk",
			data : JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			if(resp == -1){
				alert('중복확인에 실패 하였습니다.')
				idConfirmChk = false;
			}else if(resp == 1){
				alert('중복된 아이디 입니다.')
				$('#id_wrong').css('display','block');
				idConfirmChk = false;
				return
			}else{
				alert('사용할수 있는 아이디 입니다.')
				$('#id_wrong').css('display','none');
				idConfirmChk = true;
				return
			}
		})
	})
	
	$('#joinUsername').keyup(function(){
		idConfirmChk = false;
	})
	

	$('#joinForm').submit(function(){
		var joinPassword = $('#joinPassword').val();
		var joinPasswordre = $('#joinPasswordRe').val();
		var joinName = $('#name').val();
		if(!idConfirmChk){
			alert('아이디 중복확인을 해주세요')
			return false;
		}
		
		if(passwordRegularExpression(joinPassword)){
			alert('비밀번호는는 영어 + 숫자 6글자이상 입력해주세요')
			return false;
		}

		if(joinPassword != joinPasswordre){
			alert('비밀번호를 확인해 주세요')
			return false;
		}
		
		if(nameRegularExpression(joinName)){
			alert('한글 2-5글자 이름을 입력해주세요')
			return false;
		}
		return true;
	})



})

//아이디 정규식
function idRegularExpression(username){
	var regType1 = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/;
	if(regType1.test(username)){
		return false;
	}else{
		return true
	}
}

//비밀번호 정규식
function passwordRegularExpression(password){
	var regType1 = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/;
	if(regType1.test(password)){
		return false;
	}else{
		return true
	}
}

//한글 정규식(이름)
function nameRegularExpression(name){
	var regType1 = /^[가-힣]{2,5}$/;
	if(regType1.test(name)){
		return false;
	}else{
		return true
	}
}





</script>
</body>
</html>