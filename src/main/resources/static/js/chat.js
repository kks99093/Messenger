var ws;
function wsOpen(){
	var myPk = $('#myPk').val();
	var yourPk = $('#yourPk').val();
	console.log(myPk)
	if(myPk != null || myPk.trim() != ''){
		ws = new WebSocket("ws://" + location.host + "/chating/"+myPk+"&"+yourPk);
	}else{
		ws = new WebSocket("ws://" + location.host + "/chating/0");	
	}		
	wsEvt();
}

function wsEvt() {
	ws.onopen = function(data){
		//소켓이 열리면 동작
	}
	
	ws.onmessage = function(data) {
		//메세지를 받으면 동작
		var msg = data.data;
		if(msg != null && msg.type != ''){
			
			var msgData = JSON.parse(msg);
			
			if(msgData.type == "message"){
				if(msgData.myPk == $('#myPk').val()){
					$("#chating").append("<p class='me'>나 : " + msgData.msg + "</p>")		
				}else{
					$("#chating").append("<p class='others'>" + msgData.name + " : " + msgData.msg + "</p>")
				}				
			}
				
		}else{
			console.wrn("unknown type!")
		}
		
	}

	document.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ //enter press
			send();
		}
	});
}

//텍스트 메세지 보내기
function send() {	
	var data = {
			type : "message",
			roomNumber: $("#roomNumber").val(),
			myPk : $("#myPk").val(),
			name : $("#name").val(),
			msg : $("#chatting").val()
	}
	ws.send(JSON.stringify(data)); //여기서 보내는게 SocketHandller에 message.getPayload에 담김
	$('#chatting').val("");
}

wsOpen()
