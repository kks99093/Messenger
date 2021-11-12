<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal"/>       
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/mainTemp.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"> 
<script src="/js/jquery-3.4.1.min.js"></script>    
<script src="/js/mainTemp.js"></script>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
	
	<div class="page-wrapper chiller-theme toggled">
	  <nav id="sidebar" class="sidebar-wrapper">
	    <div class="sidebar-content">
	      <div class="sidebar-brand">
	        <a href="/">사이트명r</a>
	        <div id="close-sidebar">
	          <i class="fas fa-times"></i>
	        </div>
	      </div>
	      <div class="sidebar-header">
	        <div class="user-pic">
				<c:choose>
					<c:when test="${principal.userInfo.profileImg != null }">
						<img class="profile_img" src="/upload/profileImg/${principal.userInfo.profileImg}">
					</c:when>
					<c:otherwise>
						<img class="profile_img" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg" alt="User picture">
					</c:otherwise>
				</c:choose>
	          <div class="user-info">
	          	<span style="margin:5px; text-decoration: underline; cursor: pointer;" id="updProfileBtn">수정</span>
	          </div>
	        </div>
	        <div class="user-info">
	          <span class="user-name">
	            <strong>${principal.userInfo.name }</strong>
	          </span>
	          <span class="user-role">${principal.userInfo.role }</span>
	        </div>
	      </div>
	      <div class="sidebar-menu">
	        <ul>
	        	<li class="header-menu" style="cursor: pointer;" id="mainBtn">
	            	<span>메인</span>
	          	</li>
	          <li class="header-menu">
	            <span>전체</span>
	          </li>
	          <li class="sidebar-dropdown">
	            <a href="/board/team?category=4">
	              <i class="fa fa-tachometer-alt"></i>
	              <span>공지</span>
	              <!-- <span class="badge badge-pill badge-warning">New</span> -->	              
	            </a>
	          </li>
	          <li class="header-menu">
	            <span>부서</span>
	          </li>
	          <li>
	            <a href="/board/team?category=1">
	              <i class="fa fa-book"></i>
	              <span>A</span>
	            </a>
	          </li>
	          <li>
	            <a href="/board/team?category=2">
	              <i class="fa fa-calendar"></i>
	              <span>B</span>
	            </a>
	          </li>
	          <li>
	            <a href="/board/team?category=3">
	              <i class="fa fa-folder"></i>
	              <span>C</span>
	            </a>
	          </li>
	        </ul>
	      </div>
	      <!-- sidebar-menu  -->
	    </div>
	    <!-- sidebar-content  -->
	    <div class="sidebar-footer">
	      <a href="/chat/chatRoomList/">
	        <i class="fa fa-envelope"></i>
	        <span class="badge badge-pill badge-success notification">채팅방 가기</span>
	      </a>
	    </div>
	  </nav>
	  <!-- sidebar-wrapper  -->
	  <main class="page-content">
	    <div class="container">
	    	<jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
	    </div>
	  </main>
	  <!-- page-content" -->
	</div>
	<!-- page-wrapper -->
</body>
</html>