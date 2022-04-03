<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-tw">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Video player example</title>
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<link rel="stylesheet" type="text/css"
	href="/SpecialTopic/css/style.css">
<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
</style>
<script>
var clients = ["123"];
</script>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="player m-3 p-3 shadow">
			<video controls controlsList="nodownload">
				<source src="/SpecialTopic/classvideo/sintel-short.mp4"
					type="video/mp4">
				<!-- fallback content here -->
			</video>
			<div class="controls">
				<button class="btn btn-primary" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
					aria-controls="offcanvasRight">章節清單</button>
			</div>
			<input id="cid" type="hidden" value="${cid}"/>
		</div>
	</div>


	<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
		aria-labelledby="offcanvasRightLabel">
		<div class="offcanvas-header">
			<h5 id="offcanvasRightLabel">章節清單</h5>
			<button type="button" class="btn-close text-reset"
				data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<ul class="list-group">
			</ul>
		</div>
	</div>

	<script src="/SpecialTopic/js/custom-player.js"></script>
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
</html>