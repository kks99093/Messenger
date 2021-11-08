$(document).ready(function(){
	var boardPk = $('#boardPk').val();
	var category = $('#category').val();
	$('#upd_btn').click(function(){
		location.href = '/board/writeView?boardPk='+boardPk;
	})
	
	$('#del_btn').click(function(){
		if(confirm('정말 삭제하시겠습니까?')){
			location.href = '/board/delBoard?boardPk='+boardPk+'&category='+category;
		}
		return	
		
	})
		
})
	
	
function moveDetail(boardPk){
	location.href = '/board/boardDetail?boardPk='+boardPk	
}