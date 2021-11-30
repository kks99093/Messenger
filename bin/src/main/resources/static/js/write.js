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