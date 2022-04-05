<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  <!-- Head CSS -->
  <jsp:include page="../incloud/head-css.jsp" />
<script src="/SpecialTopic/js/createcurriculum.js"></script>  
<title>curriculum</title>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />

	<button onclick="creatForm()"></button>	
	<div id="createform"></div>

	<!-- ======= Footer ======= -->
  <jsp:include page="../incloud/footer-section.jsp" />

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Templete JS -->
  <jsp:include page="../incloud/body-js.jsp" />
  
</body>
</html>