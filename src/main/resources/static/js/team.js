var boardCategory = $('#boardCategory').val();
$(document).ready(function(){
	//글쓰기 버튼
	$('#board_write_btn').click(function(){
		location.href = '/board/writeView?category='+boardCategory;
	})
	
})


function moveDetail(boardPk){
	location.href = '/board/boardDetail?boardPk='+boardPk	
}

function pageMove(pageNum){
	location.href = '?category='+boardCategory+'&page='+pageNum
}