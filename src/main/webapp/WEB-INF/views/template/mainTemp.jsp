<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
       
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/mainTemp.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"> 
<script src="/js/mainTemp.js"></script>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
	
	<div class="page-wrapper chiller-theme toggled">
	  <nav id="sidebar" class="sidebar-wrapper">
	    <div class="sidebar-content">
	      <div class="sidebar-brand">
	        <a href="#">사이트명r</a>
	        <div id="close-sidebar">
	          <i class="fas fa-times"></i>
	        </div>
	      </div>
	      <div class="sidebar-header">
	        <div class="user-pic">
	          <img class="img-responsive img-rounded" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg" alt="User picture">
	        </div>
	        <div class="user-info">
	          <span class="user-name">Jhon
	            <strong>Smith</strong>
	          </span>
	          <span class="user-role">Administrator</span>
	          <span class="user-status">
	            <i class="fa fa-circle"></i>
	            <span>Online</span>
	          </span>
	        </div>
	      </div>
	      <div class="sidebar-menu">
	        <ul>
	          <li class="header-menu">
	            <span>General</span>
	          </li>
	          <li class="sidebar-dropdown">
	            <a href="#">
	              <i class="fa fa-tachometer-alt"></i>
	              <span>Dashboard</span>
	              <span class="badge badge-pill badge-warning">New</span>
	            </a>
	          </li>
	          <li class="sidebar-dropdown">
	            <a href="#">
	              <i class="fa fa-shopping-cart"></i>
	              <span>E-commerce</span>
	              <span class="badge badge-pill badge-danger">3</span>
	            </a>
	          </li>
	          <li class="sidebar-dropdown">
	            <a href="#">
	              <i class="far fa-gem"></i>
	              <span>Components</span>
	            </a>
	          </li>
	          <li class="sidebar-dropdown">
	            <a href="#">
	              <i class="fa fa-chart-line"></i>
	              <span>Charts</span>
	            </a>
	          </li>
	          <li class="header-menu">
	            <span>Extra</span>
	          </li>
	          <li>
	            <a href="#">
	              <i class="fa fa-book"></i>
	              <span>Documentation</span>
	              <span class="badge badge-pill badge-primary">Beta</span>
	            </a>
	          </li>
	          <li>
	            <a href="#">
	              <i class="fa fa-calendar"></i>
	              <span>Calendar</span>
	            </a>
	          </li>
	          <li>
	            <a href="#">
	              <i class="fa fa-folder"></i>
	              <span>Examples</span>
	            </a>
	          </li>
	        </ul>
	      </div>
	      <!-- sidebar-menu  -->
	    </div>
	    <!-- sidebar-content  -->
	    <div class="sidebar-footer">
	      <a href="#">
	        <i class="fa fa-bell"></i>
	        <span class="badge badge-pill badge-warning notification">3</span>
	      </a>
	      <a href="#">
	        <i class="fa fa-envelope"></i>
	        <span class="badge badge-pill badge-success notification">7</span>
	      </a>
	      <a href="#">
	        <i class="fa fa-cog"></i>
	        <span class="badge-sonar"></span>
	      </a>
	      <a href="#">
	        <i class="fa fa-power-off"></i>
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