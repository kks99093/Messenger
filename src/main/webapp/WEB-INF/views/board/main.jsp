<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<link rel="stylesheet" href="/css/main.css">
<script src="/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/css/main.css"> 
<script src="/js/main.js"></script>
<div class="calendar">
    <div class="cal_wrap">
        <div class="day">
            <div class="prev" onclick="calMove(${calendarInfo.month-1},${calendarInfo.year})"><i class="xi-angle-left"></i>
            </div>
            <span class="year">${calendarInfo.year }년</span>
            <span class="month">${calendarInfo.month }월</span>
            <div class="next" onclick="calMove(${calendarInfo.month+1},${calendarInfo.year})"><i class="xi-angle-right"></i>
            </div>
        </div>
        <div class="clock_div">
        	<input type="hidden" value="${attendanceChk }" id="attendanceChk">
        	<h1 class="clock_h1">현재시간 : <span class="currentTime"></span>
        	<c:choose>
        		<c:when test="${attendanceChk == 0 }">
        			<button type="button" id="attendanceBtn">출석</button>
        		</c:when>
        		<c:when test="${attendanceChk == 1 }">
        			<button type="button" id="attendanceBtn">퇴근</button>
        		</c:when>
        		<c:when test="${attendanceChk == 2 }">
        			<span style="color : blue" >퇴근 하셨습니다</span>
        		</c:when>
        	</c:choose>
        	
        	</h1>
        </div>
	     <div class="calendar_n">
	        <ul>
	            <li class="holi">일</li>
	            <li>월</li>
	            <li>화</li>
	            <li>수</li>
	            <li>목</li>
	            <li>금</li>
	            <li class="holi">토</li>
	        </ul>
	     </div>
	     <div class="calendar_subn">
			<ul>
				<c:forEach var="days" items="${calendarInfo.daysList}">
					<c:choose>
						<c:when test="${days == null }">
							<li></li>
						</c:when>
						<c:otherwise>
						<li>
							<ul style="list-style:none; font-size : 16px;">
								<li>${days}</li>
								<li>
									<c:forEach var="startAtt" items="${startAttList}">
									<c:if test="${startAtt.key == days }">
										출근 시간 : ${startAtt.value }
										</c:if>
								</c:forEach>
								</li>
								<li>
									<c:forEach var="endAtt" items="${endAttList}">
										<c:if test="${endAtt.key == days }">
											<c:choose>
												<c:when test="${endAtt.value == null }">
												</c:when>
												<c:otherwise>
													퇴근 시간 : ${endAtt.value }
												</c:otherwise>
											</c:choose>											
										</c:if>
									</c:forEach>
								</li>
							</ul>
						</li>					
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:forEach begin="1" end="${calendarInfo.remine }">
						<li>
						</li>
				</c:forEach>
			</ul>
		</div>
		
    </div>
</div>