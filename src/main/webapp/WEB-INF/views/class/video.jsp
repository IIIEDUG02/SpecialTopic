<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>瀏覽課程資訊</title>
<meta content="" name="description">
<meta content="" name="keywords">

<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
</style>
<link rel="stylesheet" type="text/css" href="css/video.css">
<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>



</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
<body>



	<main id="main" data-aos="fade-in">

		<div class="player">
			<video>
				<source src="/SpecialTopic/classvideo/123.mp4" type="video/mp4">
			</video>
			<div class="controls">
				<button class="play" data-icon="P" aria-label="play pause toggle"></button>
				<button class="stop" data-icon="S" aria-label="stop"></button>
				<div class="timer">
					<div></div>
					<span aria-label="timer">00:00</span>
				</div>
				<input type="range" class="player-sound" min="0" max="1" value="1"
					step="0.1">
				<button class="player-btn" data-skip="-4">快進4秒</button>
				<button class="player-btn" data-skip="4">後退4秒</button>
				<button class="player-btn fullscreen">全螢幕</button>
			</div>
		</div>




	</main>

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
	<script src="/SpecialTopic/js/custom-player.js"></script>

</body>
</html>