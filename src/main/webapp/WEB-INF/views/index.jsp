<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html lang="en_US">

<head>

<title>EAZY LEARN 首頁</title>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<jsp:include page="incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="incloud/head-css.jsp" />

<!-- Head js -->
<jsp:include page="incloud/head-js.jsp" />

<!-- JavaScript -->
<script src="/SpecialTopic/js/ypteacher-section.js"></script>
<script src="/SpecialTopic/js/mpteacher-section.js"></script>
<script src="/SpecialTopic/js/ypclass-section.js"></script>
<script src="/SpecialTopic/js/mpclass-section.js"></script>

<!-- =======================================================
  * Template Name: Mentor - v4.7.0
  * Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="incloud/header-section.jsp" />

	<!-- ======= Hero Section ======= -->
	<section id="hero">
		<div class="hero-container" data-aos="fade-in">
			<h1>EAZY LEARN</h1>
			<h2>學習無所不在，隨時、隨地</h2>
			<img src="/SpecialTopic/assets/img/hero-bg.jpg" alt="Hero Imgs"
				data-aos="zoom-out" data-aos-delay="100">
		</div>
	</section>
	<!-- End Hero Section -->

	<main id="main">

		<!-- ======= About Section ======= -->
		<jsp:include page="incloud/about-section.jsp" />

		<!-- ======= Trainers Section ======= -->
		<%--     <jsp:include page="incloud/trainers-section.jsp" /> --%>
		<jsp:include page="incloud/class-section.jsp" />

	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<jsp:include page="incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="incloud/body-js.jsp" />

	<!-- ======= errMsg ======= -->
	<c:if test="${not empty errMsg}">
		<script>alert("${errMsg}")</script>
	</c:if>
	<!-- ======= param.error ======= -->
	<c:if test="${param.error != null}">
		<script>alert("帳號或密碼錯誤")</script>
	</c:if>
</body>

</html>