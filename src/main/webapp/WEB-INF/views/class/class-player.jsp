<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>課程播放</title>
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

	<div class="paid-course-landing-page__container"
		style="margin-top: 71px">
	<div class="paid-course-landing-page__container "
		style="margin-top: 71px">
		<div class="top-container dark-background p-2 ">
			<div
				class="dark-background-inner-position-container d-flex justify-content-center"">
				<!-- Inner text Block -->
				<div
					class="course-landing-page__main-content dark-background-inner-text-container ">
					<!-- Inner text container -->
					<div class="udlite-text-sm clp-lead mx-auto ">
						<!-- Course title -->
						<h5
							class="udlite-heading-xl clp-lead__title clp-lead__title--small text-center mx-auto">
							${cb.getTitle()}</h5>
					</div>
				</div>
			</div>


		</div>
		<div class="container ">
			<div id="video-container " class="player m-3 ms-5 p-3 shadow "
				style="text-align: center">
				<div class="h1 mb-2" id="chapter1">${CurriculumList[0].getChapter()}</div>
				<input id="cuid" type="hidden"
					value="${CurriculumList[0].getCuid()}" /> <input id="chapter"
					type="hidden" value="${CurriculumList[0].getChapter()}" />
				<video controls controlsList="nodownload" class="m-auto">
					<source src="${CurriculumList[0].getVideo_path()}" type="video/mp4">
					<!-- fallback content here -->
				</video>
				<div class="controls">
					<button class="btn btn-success" type="button"
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
				<h5 class="mt-2" id="offcanvasRightLabel">章節清單</h5>
				<button type="button" class="btn-close text-reset"
					data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="list-group">
				</ul>
			</div>
		</div>
	</div>
		<!-- Course block -->
		<div class="paid-course-landing-page__body">


			<!-- Main content block -->
			<div class="course-landing-page__main-content">
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

				<!-- "課程內容" block -->
				<div class="component-margin">
					<!-- Course curriculum block -->
					<div class="course-curriculum">

						<c:set var="re" value="\\d+\\." />
						<c:if
							test="${not empty cb.getClassDetailsBean().getStu_required().trim().split(re)}">
							<!-- "要求" block -->
							<div class="component-margin">
								<h2 class="udlite-heading-xl requirements--title--2j7S2"
									style="margin-bottom: 1.6rem;">要求</h2>
								<ul class="unstyled-list udlite-block-list">
									<c:forEach var="require"
										items="${cb.getClassDetailsBean().getStu_required().trim().split(re)}">
										<c:if test="${not empty require}">
											<li>
												<div
													class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
													<svg aria-hidden="true" focusable="false"
														class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
												<use xlink:href="#icon-bullet"></use></svg>
													<div class="udlite-block-list-item-content">${require}</div>
												</div>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</c:if>

						<c:set var="re" value="\\d+\\." />
						<c:if
							test="${not empty classBean.getClassDetailsBean().getNeeded_tool().trim().split(re)}">
							<!-- "工具" block -->
							<div class="component-margin">
								<h2 class="udlite-heading-xl requirements--title--2j7S2"
									style="margin-bottom: 1.6rem;">工具</h2>
								<ul class="unstyled-list udlite-block-list">
									<c:forEach var="tool"
										items="${classBean.getClassDetailsBean().getNeeded_tool().trim().split(re)}">
										<c:if test="${not empty tool}">
											<li>
												<div
													class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
													<svg aria-hidden="true" focusable="false"
														class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
												<use xlink:href="#icon-bullet"></use></svg>
													<div class="udlite-block-list-item-content">${tool}</div>
												</div>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</c:if>

						<!-- "講師" block -->
						<div class="component-margin styles--instructors--2JsS3"
							style="flex-direction: column;">
							<h2 class="udlite-heading-xl styles--instructors__header--16F_8"
								style="margin: 0 0 1.6rem;">講師</h2>

							<!-- instructor block -->
							<div class="instructor--instructor--1wSOF">
								<div>
									<!-- instructor title -->
									<div
										class="udlite-heading-lg udlite-link-underline instructor--instructor__title--34ItB">
										<a href="javascript:void(0);" style="color: #5fcf80;">${instructor.getMemberInformation().getFullname()}</a>
									</div>

									<!-- instructor job -->
									<div
										class="udlite-text-md instructor--instructor__job-title--1HUmd">${instructor.getMemberInformation().getJob()}</div>
								</div>

								<!-- instructor image and states block -->
								<div class="instructor--instructor__image-and-stats--1IqE7">
									<!-- instructor image link -->
									<a class="instructor--instructor__image-link--9e3fA"> <!-- instructor avatar -->
										<img alt="Frank BAI"
										class="instructor--instructor__image--va-P5 udlite-avatar udlite-avatar-image"
										width="64" height="64"
										src="/SpecialTopic/img/default_avatar.png"
										style="width: 6.4rem; height: 6.4rem;">
									</a>

									<!-- instructor item list -->
									<ul class="unstyled-list udlite-block-list">
										<li>
											<div
												class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm"
												style="padding: 0.4rem 0;">
												<svg aria-hidden="true" focusable="false"
													class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
											<use xlink:href="#icon-rating-star"></use></svg>
												<div class="udlite-block-list-item-content"
													style="min-height: 1.96rem; margin-left: 1.6rem;">講師評等
													0.0</div>
											</div>
										</li>

										<li>
											<div
												class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm"
												style="padding: 0.4rem 0;">
												<svg aria-hidden="true" focusable="false"
													class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
											<use xlink:href="#icon-certificate"></use></svg>
												<div class="udlite-block-list-item-content"
													style="min-height: 1.96rem; margin-left: 1.6rem;">0
													則評論</div>
											</div>
										</li>

										<li>
											<div data-purpose="stat"
												class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm"
												style="padding: 0.4rem 0;">
												<svg aria-hidden="true" focusable="false"
													class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
											<use xlink:href="#icon-people"></use></svg>
												<div class="udlite-block-list-item-content"
													style="min-height: 1.96rem; margin-left: 1.6rem;">0
													位學生</div>
											</div>
										</li>

										<li>
											<div data-purpose="stat"
												class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm"
												style="padding: 0.4rem 0;">
												<svg aria-hidden="true" focusable="false"
													class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
											<use xlink:href="#icon-play"></use></svg>
												<div class="udlite-block-list-item-content"
													style="min-height: 1.96rem; margin-left: 1.6rem;">0
													門課程</div>
											</div>
										</li>
									</ul>
								</div>

								<!-- instructor description block -->
								<div
									class="udlite-text-sm instructor--instructor__description--1dHxF"
									style="margin-top: 1.6rem; margin-bottom: 0.8rem;">
									<div class="show-more--container--3W59b">
										<div class="show-more--content--2BLF7"
											style="max-height: 14.6rem;">
											<div>
												<div>
													<p>
														具有相當豐富的業界實戰與教學經驗，曾參與連鎖3C通訊零售商、電子商務、行動支付等多間國內知名上市公司，打造多款行動應用程式。
														老師總是不藏私，經常分享獨家珍藏心得筆記，在 Facebook、Youtube、Dcard 和 Ptt
														等社群媒體跟大家分享交流最新技術文章， 帶給大家第一手的完整新技術、讓大家的知識與產業界實際應用層面上確切接軌。</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<svg
				style="position: absolute; width: 0; height: 0; overflow: hidden;"
				version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink"
				xmlns="http://www.w3.org/2000/svg">
    <defs>
      <symbol id="icon-rating-star" viewBox="0 0 24 24">
		<path
					d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21 12 17.27z"></path></symbol>
      <symbol id="icon-language" viewBox="0 0 24 24">
		<path
					d="M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zm6.93 6h-2.95a15.65 15.65 0 00-1.38-3.56A8.03 8.03 0 0118.92 8zM12 4.04c.83 1.2 1.48 2.53 1.91 3.96h-3.82c.43-1.43 1.08-2.76 1.91-3.96zM4.26 14C4.1 13.36 4 12.69 4 12s.1-1.36.26-2h3.38c-.08.66-.14 1.32-.14 2s.06 1.34.14 2H4.26zm.82 2h2.95c.32 1.25.78 2.45 1.38 3.56A7.987 7.987 0 015.08 16zm2.95-8H5.08a7.987 7.987 0 014.33-3.56A15.65 15.65 0 008.03 8zM12 19.96c-.83-1.2-1.48-2.53-1.91-3.96h3.82c-.43 1.43-1.08 2.76-1.91 3.96zM14.34 14H9.66c-.09-.66-.16-1.32-.16-2s.07-1.35.16-2h4.68c.09.65.16 1.32.16 2s-.07 1.34-.16 2zm.25 5.56c.6-1.11 1.06-2.31 1.38-3.56h2.95a8.03 8.03 0 01-4.33 3.56zM16.36 14c.08-.66.14-1.32.14-2s-.06-1.34-.14-2h3.38c.16.64.26 1.31.26 2s-.1 1.36-.26 2h-3.38z"></path></symbol>
      <symbol id="icon-tick" viewBox="0 0 24 24">
		<path d="M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41L9 16.17z"></path></symbol>
      <symbol id="icon-expand" viewBox="0 0 24 24">
		<path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6-1.41-1.41z"></path></symbol>
      <symbol id="icon-play" viewBox="0 0 24 24">
		<path
					d="M2 12c0 5.525 4.475 10 10 10s10-4.475 10-10S17.525 2 12 2 2 6.475 2 12zm15 .114L9 16V8l8 4.114z"></path></symbol>
      <symbol id="icon-bullet" viewBox="0 0 24 24">
		<path d="M17 12a5 5 0 11-10.001-.001A5 5 0 0117 12z"></path></symbol>
      <symbol id="icon-certificate" viewBox="0 0 24 24">
		<path
					d="M20.39 19.37L16.38 18 15 22l-3.08-6L9 22l-1.38-4-4.01 1.37 2.92-6A6.97 6.97 0 015 9a6.999 6.999 0 1114 0c0 1.65-.57 3.17-1.53 4.37l2.92 6zM7 9l2.69 1.34-.19 3 2.5-1.66 2.5 1.65-.17-2.99L17 9l-2.68-1.35.18-2.98L12 6.31 9.5 4.65l.17 3.01L7 9z"></path></symbol>
      <symbol id="icon-people" viewBox="0 0 24 24">
		<path
					d="M16 10c1.66 0 2.99-1.34 2.99-3S17.66 4 16 4c-1.66 0-3 1.34-3 3s1.34 3 3 3zm-8 0c1.66 0 2.99-1.34 2.99-3S9.66 4 8 4C6.34 4 5 5.34 5 7s1.34 3 3 3zm0 2c-2.33 0-7 1.17-7 3.5V20h14v-4.5c0-2.33-4.67-3.5-7-3.5zm8 0c-.29 0-.62.02-.97.05 1.16.84 1.97 1.97 1.97 3.45V20h6v-4.5c0-2.33-4.67-3.5-7-3.5z"></path></symbol>
      <symbol id="icon-play-overlay" viewBox="0 0 24 24">
		<circle cy="12" cx="12" fill="#1e1e1c" r="10"></circle>
		<path
					d="M0 12A12 12 0 1012 0 12 12 0 000 12zm18 .137L8.4 16.8V7.2l9.6 4.937z"></path></symbol>
      <symbol id="icon-video" viewBox="0 0 24 24">
		<path
					d="M21 3H3c-1.11 0-2 .89-2 2v12a2 2 0 002 2h5v2h8v-2h5c1.1 0 1.99-.9 1.99-2L23 5a2 2 0 00-2-2zm0 14H3V5h18v12zm-5-6l-7 4V7l7 4z"></path></symbol>
      <symbol id="icon-lifetime" viewBox="0 0 24 24">
		<path
					d="M18.6 6.62c-1.44 0-2.8.56-3.77 1.53L12 10.66 10.48 12h.01L7.8 14.39c-.64.64-1.49.99-2.4.99-1.87 0-3.39-1.51-3.39-3.38S3.53 8.62 5.4 8.62c.91 0 1.76.35 2.44 1.03l1.13 1 1.51-1.34L9.22 8.2A5.37 5.37 0 005.4 6.62C2.42 6.62 0 9.04 0 12s2.42 5.38 5.4 5.38c1.44 0 2.8-.56 3.77-1.53l2.83-2.5.01.01L13.52 12h-.01l2.69-2.39c.64-.64 1.49-.99 2.4-.99 1.87 0 3.39 1.51 3.39 3.38s-1.52 3.38-3.39 3.38c-.9 0-1.76-.35-2.44-1.03l-1.14-1.01-1.51 1.34 1.27 1.12a5.386 5.386 0 003.82 1.57c2.98 0 5.4-2.41 5.4-5.38s-2.42-5.37-5.4-5.37z"></path></symbol>
      <symbol id="icon-mobile" viewBox="0 0 24 24">
		<path
					d="M17 1.01L7 1c-1.1 0-2 .9-2 2v18c0 1.1.9 2 2 2h10c1.1 0 2-.9 2-2V3c0-1.1-.9-1.99-2-1.99zM17 19H7V5h10v14z"></path></symbol>
      <symbol id="icon-trophy" viewBox="0 0 24 24">
		<path
					d="M19 5h-2V3H7v2H5c-1.1 0-2 .9-2 2v1c0 2.55 1.92 4.63 4.39 4.94A5.01 5.01 0 0011 15.9V19H7v2h10v-2h-4v-3.1a5.01 5.01 0 003.61-2.96C19.08 12.63 21 10.55 21 8V7c0-1.1-.9-2-2-2zM5 8V7h2v3.82C5.84 10.4 5 9.3 5 8zm7 6c-1.65 0-3-1.35-3-3V5h6v6c0 1.65-1.35 3-3 3zm7-6c0 1.3-.84 2.4-2 2.82V7h2v1z"></path></symbol>
    </defs>
  </svg>

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