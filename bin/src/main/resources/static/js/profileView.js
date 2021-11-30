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