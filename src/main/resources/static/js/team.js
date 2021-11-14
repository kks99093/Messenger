$(document).ready(function(){
	//글쓰기 버튼
	$('#board_write_btn').click(function(){
		location.href = '/board/writeView?category='+boardCategory;
	})
	
	var boardCategory = $('#boardCategory').val();
	
})


function moveDetail(boardPk){
	var boardCategory = $('#boardCategory').val()
	location.href = '/board/boardDetail?boardPk='+boardPk+'&category='+boardCategory;
}

function pageMove(pageNum){
	location.href = '?category='+boardCategory+'&page='+pageNum
}

function userChatClick(userPk){			
		var myPk = $('#myPk').val();
		var yourPk = userPk;
		var url = "/chat/chat?myPk="+myPk+"&yourPk="+yourPk;
		var popNm = "chat"
		var options = 'width=500, height=700, scrollbars= yes, status=no, menubar=no, toolbar=no, resizable=no';			
		window.open(url, popNm, options);
}