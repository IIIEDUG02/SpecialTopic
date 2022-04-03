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
			<video controlsList="nodownload">
				<source src="/SpecialTopic/classvideo/sintel-short.mp4"
					type="video/mp4">
				<!-- fallback content here -->
			</video>
			<div class="controls">
				<button class="play" aria-label="play pause toggle">play</button>
				<div class="timer" style="display: inline-block;">
					<div></div>
					<span aria-label="timer">00:00</span>
				</div>
				<button class="rwd" aria-label="rewind">reward</button>
				<button class="fwd" aria-label="fast forward">foward</button>
				<button class="fullscreen" aria-label="fast forward">fullscreen</button>
				<button class="btn btn-primary" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
					aria-controls="offcanvasRight">Toggle right offcanvas</button>
			</div>
		</div>
	</div>


	<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
		aria-labelledby="offcanvasRightLabel">
		<div class="offcanvas-header">
			<h5 id="offcanvasRightLabel">Offcanvas right</h5>
			<button type="button" class="btn-close text-reset"
				data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<ul class="list-group">
			  <li class="list-group-item active" aria-current="true">An active item</li>
			  <li class="list-group-item">A second item</li>
			  <li class="list-group-item">A third item</li>
			  <li class="list-group-item">A fourth item</li>
			  <li class="list-group-item">And a fifth one</li>
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