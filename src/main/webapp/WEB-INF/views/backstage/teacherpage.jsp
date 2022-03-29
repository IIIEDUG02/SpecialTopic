<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  <!-- Head CSS -->
  <jsp:include page="../incloud/head-css.jsp" />
  
  <!-- jQuery -->
  <script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  
<title>teacherpage</title>
</head>
<body>

	<jsp:include page="../incloud/header-section.jsp" />
	
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
					<thead>S
						<tr>
							<td>課程名稱:</td>
							<td>課程分類:</td>
							<td>操作:</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tc" items="${classes}">
							<tr>
								<td><c:out value="${tc.getTitle()}" /></td>
								<td><c:out value="${tc.getClassType()}" /></td>
								<td><input type="button"
									onclick="javascript:window.location = '/SpecialTopic/update/${tc.getCid()}'"
									value="編輯" />
									<input type="button"
									onclick="javascript:window.location = '/SpecialTopic/deleteclass/${tc.getCid()}'"
									value="下架課程" />
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
	
	<!-- ======= Footer ======= -->
  <jsp:include page="../incloud/footer-section.jsp" />

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Templete JS -->
  <jsp:include page="../incloud/body-js.jsp" />
  
</body>
</html>