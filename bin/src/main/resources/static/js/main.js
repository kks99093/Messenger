$(document).ready(function(){
	var attendanceChk = $("#attendanceChk").val();
	setInterval(currentTime, 1000);
	
	$("#attendanceBtn").click(function(){
		var currentTime = new Date();
		var currentYear = currentTime.getFullYear();
		var currentMonth = currentTime.getMonth()+1;
		var currentDate = currentTime.getDate();
		var currentHour = currentTime.getHours();
		var currentMinutes = currentTime.getMinutes();
		var currentSeconds = currentTime.getSeconds();
		
		var data = attendanceChk
		if(attendanceChk == 0){
			data = {
				year : currentYear,
				month : currentMonth,
				day : currentDate,
				startHour : currentHour,
				startMinute : currentMinutes,
				state : attendanceChk
			}	
		}else if(attendanceChk == 1){
			data = {
				year : currentYear,
				month : currentMonth,
				day : currentDate,
				endHour : currentHour,
				endMinute : currentMinutes,
				state : attendanceChk
			}	
		}
		if(data == 2){
			alert("이미 퇴근하셨습니다")
		}
		
		$.ajax({
			type : "POST",
			url: "/auth/attendance",
			data : JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			if(resp == 0){
				location.reload();
			}
		})
	})
})
		

function calMove(calMonth,calYear){
	if(calMonth == 0){
		calMonth = 12;
		calYear = calYear - 1;
	}
	
	if(calMonth == 13){
		calMonth = 1;
		calYear = calYear + 1;
	}
	location.href = "/board/main?month="+calMonth+"&year="+calYear;
}

function currentTime(){
	var currentTime = new Date();
	var currentYear = currentTime.getFullYear();
	var currentMonth = currentTime.getMonth()+1;
	var currentDate = currentTime.getDate();
	var currentHour = currentTime.getHours();
	var currentMinutes = currentTime.getMinutes();
	var currentSeconds = currentTime.getSeconds();
    $(".currentTime").html(currentYear+"년 " + currentMonth + "월 " + currentDate + "일 " +  currentHour +":" + currentMinutes + ":"+currentSeconds);    
}
//$(".currentTime").html(`\${currentYear}년 \${currentMonth}월 \${currentDate}일 \${currentHour}:\${currentMinutes}:\${currentSeconds}`);