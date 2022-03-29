<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"            prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"             prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>課程銷售紀錄</title>
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

</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs" data-aos="fade-in"></div>
	<!-- End Breadcrumbs -->

	<!-- main start -->
	<div class="container">
		<!-- Articles block -->
		<div class="article-block">

			<!-- Articles container -->
			<div class="d-grid p-left">
				<!-- 使用 for 迴圈取出每筆售課紀錄在頁面上 -->
				<c:forEach var="classBean" items="${class_c2bList_Map.keySet()}">
					<div class="shadow p-3 mb-5 bg-body rounded">
						<div class="pb-and-bb">
							<!-- Article head -->
							<div class="d-flex">
								<!-- authorMeta -->
								<div id="authorMeta" class="author-meta">
									<div class="author-meta__author"></div>
									<!-- 取出發布文章的人，顯示 username -->
									<span class="p-2 rounded bg-success text-white"> <c:out
											value="${classBean.getTitle()} 售課紀錄" />
									</span>
								</div>
							</div>
							<table class="table">
								<thead>
									<tr>
										<th scope="col">學生ID</th>
										<th scope="col">購課日期</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${class_c2bList_Map.get(classBean)}"
										var="c2bBean">
										<tr>
											<td class="col"><c:out value="${c2bBean.getUid()}" /></td>
											<td class="col"><c:out value="${c2bBean.getOrderDate()}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- main end -->

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />


	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

	<!-- Toast js -->
	<script src="/SpecialTopic/js/toast.js"></script>

	<script>
		if (window.location.href.endsWith('?create=success'))
			showToast('您的文章已發佈成功！')
	</script>
</body>
</html>