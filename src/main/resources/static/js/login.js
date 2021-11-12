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

