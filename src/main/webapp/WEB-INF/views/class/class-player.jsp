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


<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<title>Video player example</title>
<!-- Animate CSS -->
<link rel="stylesheet"
	href="/SpecialTopic/assets/vendor/animate.css/animate.min.css" />
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- viewClass CSS -->
<link rel="stylesheet" href="/SpecialTopic/css/view-class.css" />

<style>
html {
	font-size: 62.5%;
}

body {
	font-family: "PingFang TC", 微軟正黑體, sans-serif !important;
	font-weight: 400 !important;
	line-height: 1.4 !important;
	font-size: 1.6rem !important;
	color: #1c1d1f !important;
}

.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}

div#shoppingcart {
	text-align: center;
}

video {
	height: 480px;
	width: 850px;
}

h1 {
	font-size: 40px;
}

.position_fixed {
	position: fixed;
}

.list-group-item:hover {
	background-color: #4F9D9D
}

.height100 {
	height: 20px;
}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="paid-course-landing-page__container "
		style="margin-top: 71px">
		<div class="top-container dark-background p-2">
			<div class="dark-background-inner-position-container">
				<div>
					<div
						class="course-landing-page__main-content course-landing-page__topic-menu dark-background-inner-text-container">
					</div>
					<!-- Inner text Block -->
					<div
						class="course-landing-page__main-content dark-background-inner-text-container">
						<!-- Inner text container -->
						<div class="udlite-text-sm clp-lead">
							<!-- Course title -->
							<h5
								class="udlite-heading-xl clp-lead__title clp-lead__title--small text-center">
								${cb.getTitle()}</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container ">
			<div id="video-container justify-content-center"
				class="player m-3 ms-5 p-3 shadow">
				<div id="chapter1">${CurriculumList[0].getChapter()}</div>
				<input id="cuid" type="hidden"
					value="${CurriculumList[0].getCuid()}" />
				<input id="chapter" type="hidden"
					value="${CurriculumList[0].getChapter()}" />
				<video controls controlsList="nodownload" class="m-auto">
					<source src="${CurriculumList[0].getVideo_path()}" type="video/mp4">
					<!-- fallback content here -->
				</video>
				<div class="controls">
					<button class="btn btn-primary" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
						aria-controls="offcanvasRight">章節清單</button>
				</div>
				<input id="cid" type="hidden" value="${cid}" />
			</div>
		</div>
		<div class="offcanvas offcanvas-end" style="z-index: 10000"
			tabindex="-1" id="offcanvasRight"
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
		<!-- Main content block -->
		<div class="course-landing-page__main-content m-auto">
			<!-- "您會學到" block -->
			<div
				class="component-margin what-you-will-learn--what-will-you-learn--mnJ5T">
				<h2 class="udlite-heading-xl what-you-will-learn--title--hropy">您會學到</h2>

				<!-- "您會學到" content block -->
				<div class="what-you-will-learn--content-spacing--3btHJ">
					<!-- "您會學到" content items -->
					<ul
						class="unstyled-list udlite-block-list what-you-will-learn--objectives-list--2cWZN">
						<c:set var="re" value="\\d+\\." />
						<c:if
							test="${not empty cb.getClassDetailsBean().getGoal().trim().split(re)}">
							<c:forEach var="goal"
								items="${cb.getClassDetailsBean().getGoal().trim().split(re)}">
								<c:if test="${not empty goal}">
									<li>
										<div
											class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
											<svg aria-hidden="true" focusable="false"
												class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
													<use xlink:href="#icon-tick"></use></svg>
											<div class="udlite-block-list-item-content">
												<span>${goal}</span>
											</div>
										</div>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	
	</div>
	<div class="height100"></div>
	<div class="height100"></div>
	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
	<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
	<script src="/SpecialTopic/js/custom-player.js"></script>
</body>
</html>