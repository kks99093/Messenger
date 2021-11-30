function chatClick(myPk, yourPk){
	var url = "/chat/chat?myPk="+myPk+"&yourPk="+yourPk;
	var popNm = "chat"
	var options = 'width=500, height=700, scrollbars= yes, status=no, menubar=no, toolbar=no, resizable=no';			
	window.open(url, popNm, options);
}