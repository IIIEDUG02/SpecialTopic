<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>授課紀錄</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<!-- =======================================================
  * Template Name: Mentor - v4.7.0
  * Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />

	<main id="main">
		<!-- ======= Breadcrumbs ======= -->
		<div class="breadcrumbs" data-aos="fade-in"></div>
		<!-- End Breadcrumbs -->

		<section id="about" class="about">
			<div class="container" data-aos="fade-up">
					<c:forEach items="${cid_c2bList_Map.keySet()}" var="key">
						<div style="display: inline" class="row">
							<table
								style="border-width: 1px; border-style: dashed; border-color: #000000; padding: 5px;">
								<thead>
									<tr style="border-width: 1px; border-style: dashed; border-color: #000000; padding: 5px;">
										<td colspan="2"><c:out value="${key} 售課紀錄" /></td>
									</tr>
									<tr>
										<td>學生ID</td>
										<td>購課日期</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cid_c2bList_Map.get(key)}" var="c2bBean">
										<tr>
											<td><c:out value="${c2bBean.getUid()}" /></td>
											<td><c:out value="${c2bBean.getOrderDate()}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:forEach>
			</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
</body>
</html>