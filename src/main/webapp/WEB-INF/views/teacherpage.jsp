<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<!-- Vendor CSS Files -->
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/aos/aos.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<title>teacherpage</title>
</head>
<body>

	<jsp:include page="incloud/header-section.jsp" />
	<main id="main">
		<div class="breadcrumbs" data-aos="fade-in">
			<div class="container">
				<h2>About Us</h2>
				<p>Est dolorum ut non facere possimus quibusdam eligendi
					voluptatem. Quia id aut similique quia voluptas sit quaerat
					debitis. Rerum omnis ipsam aperiam consequatur laboriosam nemo
					harum praesentium.</p>
			</div>
		</div>
		<!-- End Breadcrumbs -->

		<section id="testimonials" class="testimonials">
			<div class="container" data-aos="fade-up">
				<table>
					<thead>
						<tr>
							<td>課程名稱:</td>
							<td>課程分類:</td>
							<td>操作:</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tc" items="${teacherclass}">
							<tr>
								<td><c:out value="${tc.getTitle()}" /></td>
								<td><c:out value="${tc.getClassType()}" /></td>
								<td><input type="button"
									onclick="javascript:window.location = '/SpecialTopic/update/${tc.getCid()}'"
									value="編輯" />
									<input type="button"
									onclick="javascript:window.location = '/SpecialTopic/deleteclass/${tc.getCid()}'"
									value="刪除課程" />
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="button" onclick="javascript:window.location = '/'"
					value="返回首頁" />
			</div>
		</section>
	</main>
	<!-- 頁尾 -->
	<jsp:include page="incloud/footer-section.jsp" />





	<!-- Vendor JS Files -->
	<script src="assets/vendor/purecounter/purecounter.js"></script>
	<script src="assets/vendor/aos/aos.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>
</html>