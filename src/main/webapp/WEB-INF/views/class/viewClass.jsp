<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>瀏覽課程資訊</title>
<meta content="" name="description">
<meta content="" name="keywords">

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

h1 {
	font-size: 40px;
}
</style>
<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />

<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />


<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<!-- 購物車 -->
<script src="/SpecialTopic/js/shopping_cart.js"></script>

<!-- Animate CSS -->
<link rel="stylesheet"
	href="/SpecialTopic/assets/vendor/animate.css/animate.min.css" />

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- viewClass CSS -->
<link rel="stylesheet" href="/SpecialTopic/css/view-class.css" />

<!-- courseComment CSS -->
<link rel="stylesheet" href="/SpecialTopic/css/course-comment.css" />
</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />

	<div class="paid-course-landing-page__container"
		style="margin-top: 71px">
		<div class="sidebar-container" style="z-index: 9999">
			<!-- sideba rcontainer block-->
			<div class="course-landing-page_sidebar-container"
				style="position: fixed; margin-top: 70px;">
				<div class="sidebar-container--content--gsvyJ" style="z-index: 9999">
					<div class="sidebar-container--content-group--1upV8">
						<!-- sidebar container introduction asset -->
						<div class="sidebar-container--introduction-asset--5ckuC">
							<div class="intro-asset--wrapper--zDTjg"
								style="background-color: #1c1d1f; width: 100%;">
								<div class="intro-asset--asset--1eSsi"
									style="position: relative; background-color: #fff;">
									<button
										class="udlite-btn udlite-btn-large udlite-btn-ghost udlite-heading-md udlite-custom-focus-visible intro-asset--placeholder--16yPA">
										<!-- intro asset img aspect -->
										<span class="intro-asset--img-aspect--1UbeZ"> <img
											style="background-size: cover; background-image: url(/SpecialTopic/assets/img/course-1.jpg);" />
										</span>

										<!-- intro asset img overlay -->
										<span
											class="intro-asset--overlay--3Z3co intro-asset--gradient--Od7zs"></span>

										<!-- intro play overlay -->
										<span class="udlite-play-overlay"> <svg
												aria-hidden="true" focusable="false"
												class="udlite-icon udlite-icon-xxlarge udlite-focus-visible-target">
												<use xlink:href="#icon-play-overlay"></use></svg>
										</span>

										<!-- intro overlay text -->
										<span
											class="udlite-heading-md intro-asset--overlay--3Z3co intro-asset--text--2vUCP">預覽此課程</span>
									</button>
								</div>
							</div>
						</div>

						<!-- sidebar container purchase section -->
						<div class="sidebar-container--purchase-section--17KRp"
							style="padding: 2.4rem;">
							<div
								class="purchase-section-container--purchase-section-container--13B-7">
								<div class="generic-purchase-section--main-cta-container--3xxeM">
									<div class="generic-purchase-section--buy-box-main--siIXV">
										<div class="buy-box--buy-box--3d_i8">
											<!-- Buy box item -->
											<div class="buy-box--buy-box-item--1Qbkl">
												<div>
													<!-- Price text container -->
													<div
														class="price-text--container--103D9 udlite-clp-price-text">
														<div
															class="price-text--price-part--2npPm udlite-clp-discount-price udlite-heading-xxl">
															<span class="udlite-sr-only">目前價格</span> <span><span>$${classBean.getPrice()}</span></span>
														</div>
													</div>
												</div>
											</div>

											<!-- add to cart button wrapper -->
											<div
												class="buy-box--buy-box-item--1Qbkl buy-box--add-to-cart-button-wrapper--1VwJj">
												<div id="add-to-cart" data-purpose="add-to-cart"
													style="flex: 1;">
													<c:choose>
														<c:when test="${not empty classManagerBean}">
															<h3>課程已購買</h3>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${not empty ShoppingCart}">
																	<button id="sc_btn_${classBean.getCid()}"
																		onclick="sc_del(${classBean.getCid()})"
																		class="udlite-btn udlite-btn-large udlite-btn-brand udlite-heading-md add-to-cart"
																		style="background: #3ac162; width: 100%; color: #fff;">
																		從購物車中移出</button>
																</c:when>
																<c:otherwise>
																	<sec:authorize access="!isAuthenticated()">
																		<button
																			class="disabled udlite-btn udlite-btn-large udlite-btn-brand udlite-heading-md add-to-cart"
																			style="opacity: .65; cursor: not-allowed; background: #3ac162; width: 100%; color: #fff;">
																			登入後才可加入至購物車</button>
																	</sec:authorize>
																	<sec:authorize access="isAuthenticated()">
																		<button id="sc_btn_${classBean.getCid()}"
																			onclick="sc_add(${classBean.getCid()})"
																			class="udlite-btn udlite-btn-large udlite-btn-brand udlite-heading-md add-to-cart"
																			style="background: #3ac162; width: 100%; color: #fff;">
																			新增至購物車</button>
																	</sec:authorize>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</div>
									<!-- money-back-guarantee -->
									<div class="generic-purchase-section--local-incentive--HzlK6">
										<div class="money-back-guarantee--money-back-guarantee--16UWd"
											style="margin-top: 1.6rem; margin-bottom: 0.8rem;">
											<span class="money-back"
												style="display: block; font-size: 1.2rem; text-align: center;">
												30 天退款保證 </span>
										</div>
									</div>

									<!-- incentives incentives container -->
									<div class="generic-purchase-section--local-incentive--HzlK6">
										<div class="incentives--incentives-container--CUQ8q"
											style="padding-top: 1.6rem;">
											<h2 class="udlite-heading-md incentives--header--3O_-f"
												data-purpose="header" style="margin-bottom: 0.8rem;">
												此課程包括：</h2>
											<ul class="unstyled-list udlite-block-list">
												<li>
													<div
														class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
														<svg aria-hidden="true" focusable="false"
															class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
															<use xlink:href="#icon-video"></use></svg>
														<div class="udlite-block-list-item-content"
															style="margin-left: 1.6rem; min-height: 1.96rem;">
															<span data-purpose="video-content-length">${classBean.getCurriculumbean().size()}
																小時的隨選影片</span>
														</div>
													</div>
												</li>

												<li>
													<div
														class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
														<svg aria-hidden="true" focusable="false"
															class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
															<use xlink:href="#icon-lifetime"></use></svg>
														<div class="udlite-block-list-item-content"
															style="margin-left: 1.6rem; min-height: 1.96rem;">
															<span data-purpose="video-content-length">完整終身存取權</span>
														</div>
													</div>
												</li>

												<li>
													<div
														class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
														<svg aria-hidden="true" focusable="false"
															class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
															<use xlink:href="#icon-mobile"></use></svg>
														<div class="udlite-block-list-item-content"
															style="margin-left: 1.6rem; min-height: 1.96rem;">
															<span data-purpose="video-content-length">透過行動裝置與電視存取</span>
														</div>
													</div>
												</li>

												<li>
													<div
														class="udlite-block-list-item udlite-block-list-item-small udlite-block-list-item-tight udlite-block-list-item-neutral udlite-text-sm">
														<svg aria-hidden="true" focusable="false"
															class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
															<use xlink:href="#icon-trophy"></use></svg>
														<div class="udlite-block-list-item-content"
															style="margin-left: 1.6rem; min-height: 1.96rem;">
															<span data-purpose="video-content-length">結業證書</span>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="top-container dark-background">
			<div class="dark-background-inner-position-container">
				<div>
					<div
						class="course-landing-page__main-content course-landing-page__topic-menu dark-background-inner-text-container">
						<!-- breadcrumb block -->
						<div class="topic-menu topic-menu-condensed udlite-breadcrumb">
							<a href="/SpecialTopic/courses" class="udlite-heading-sm">所有課程</a>
							<span class="udlite-breadcrumb-icon">&gt;</span> <a
								href="/SpecialTopic/courses" class="udlite-heading-sm">程式設計</a>
						</div>
					</div>

					<!-- Inner text Block -->
					<div
						class="course-landing-page__main-content dark-background-inner-text-container">
						<!-- Inner text container -->
						<div class="udlite-text-sm clp-lead">
							<!-- Course title -->
							<h1
								class="udlite-heading-xl clp-lead__title clp-lead__title--small">
								${classBean.getTitle()}</h1>

							<!-- ratings block -->
							<div class="clp-lead__badge-ratings-enrollment">
								<!-- ratings container -->
								<div class="clp-lead__element-item clp-lead__element-item--row">
									<!-- stars block -->
									<a href="javascript:void(0);"
										class="styles--rating-wrapper--5a0Tr"> <!-- stars container -->
										<span
										class="star-rating--star-wrapper--1QyBg star-rating--medium--17tJo star-rating--dark-background--3tENo">
											<span class="udlite-sr-only">評等︰0.0/5</span> <!-- ratings score text -->
											<span
											class="udlite-heading-sm star-rating--rating-number--2o8YM">0.0</span>

											<!-- stars icon --> <svg aria-hidden="true" width="100%"
												height="100%" viewBox="0 0 70 14" fill="none"
												xmlns="http://www.w3.org/2000/svg">
                        <mask id="u152-star-rating-mask--28"
													data-purpose="star-rating-mask">
                        <rect x="0" y="0" width="0%" height="100%"
													fill="white"></rect>
                        </mask>
                        <g class="star-rating--star-filled--24lmQ"
													mask="url(#u152-star-rating-mask--28)"
													data-purpose="star-filled">
                          <use xlink:href="#icon-rating-star" width="14"
													height="14" x="0"></use>
                          <use xlink:href="#icon-rating-star" width="14"
													height="14" x="14"></use>
                          <use xlink:href="#icon-rating-star" width="14"
													height="14" x="28"></use>
                          <use xlink:href="#icon-rating-star" width="14"
													height="14" x="42"></use>
                          <use xlink:href="#icon-rating-star" width="14"
													height="14" x="56"></use>
                        </g>
                        <g fill="transparent"
													class="star-rating--star-bordered--1EkpX" stroke-width="2"
													data-purpose="star-bordered">
                          <use xlink:href="#icon-rating-star" width="12"
													height="12" x="1" y="1"></use>
                          <use xlink:href="#icon-rating-star" width="12"
													height="12" x="15" y="1"></use>
                          <use xlink:href="#icon-rating-star" width="12"
													height="12" x="29" y="1"></use>
                          <use xlink:href="#icon-rating-star" width="12"
													height="12" x="43" y="1"></use>
                          <use xlink:href="#icon-rating-star" width="12"
													height="12" x="57" y="1"></use>
                        </g>
                      </svg>
									</span> <span>(0 個評等)</span>
									</a>

									<!-- Student count -->
									<div class="enrollment" data-purpose="enrollment">0 位學生</div>
								</div>
							</div>

							<!-- creator block -->
							<div class="clp-lead__element-item">
								<!-- instructor links container -->
								<div class="instructor-links--instructor-links--3d8_F">
									<span class="udlite-text-sm">建立者：</span> <a
										class="udlite-btn udlite-btn-large udlite-btn-link udlite-heading-md udlite-text-sm udlite-instructor-links"
										data-position="1" href="#instructor-1"> <span>${instructor.getMemberInformation().getFullname()}</span>
									</a>
								</div>
							</div>

							<!-- Meta data block -->
							<div class="clp-lead__element-meta">
								<div class="clp-lead__element-item clp-lead__locale">
									<svg aria-hidden="true" focusable="false"
										class="udlite-icon udlite-icon-xsmall icon">
										<use xlink:href="#icon-language"></use></svg>
									Traditional Chinese
								</div>
							</div>
						</div>

					</div>

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
								test="${not empty classBean.getClassDetailsBean().getGoal().trim().split(re)}">
								<c:forEach var="goal"
									items="${classBean.getClassDetailsBean().getGoal().trim().split(re)}">
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
						<h2 class="udlite-heading-xl curriculum--curriculum-header--3LSfi">課程內容</h2>

						<!-- curriculum sub header block -->
						<div class="curriculum--curriculum-sub-header--3_-6E">
							<div class="udlite-text-sm">
								<span class="curriculum--content-length--1XzLS">
									${classBean.getCurriculumbean().size()} 個章節 •
									${classBean.getCurriculumbean().size() * 10 + 2} 堂講座 • <span>總長度：<span>${classBean.getCurriculumbean().size()}&nbsp;小時&nbsp;49&nbsp;分</span></span>
								</span>
							</div>
						</div>

						<!-- curriculum body block -->
						<div>
							<!-- section panel block -->
							<c:forEach var="curriculum"
								items="${classBean.getCurriculumbean()}" varStatus="loop">
								<div class="section--panel--1tqxC panel--panel--3uDOH">
									<!-- Bootstrap collapse button -->
									<div data-bs-toggle="collapse"
										data-bs-target="#collapse${curriculum.getCuid()}"
										aria-expanded="false"
										aria-controls="collapse${curriculum.getCuid()}">
										<div
											class="udlite-btn udlite-btn-large udlite-btn-link udlite-heading-md udlite-accordion-panel-toggler panel--panel-toggler--30J_B panel--outer-panel-toggler--3I6w6">
											<!-- accordion panel heading -->
											<h3 class="udlite-accordion-panel-heading">
												<button
													class="udlite-btn udlite-btn-large udlite-btn-link udlite-heading-md js-panel-toggler panel--panel-toggler--30J_B">
													<span class="udlite-accordion-panel-title"> <span
														class="section--section-title--8blTh">${curriculum.getChapter()}</span>
														<span
														class="udlite-text-sm section--hidden-on-mobile--171Q9 section--section-content--9kwnY"
														data-purpose="section-content">
															${curriculum.getCuid() % 10 + 2} 堂講座 • <span>${curriculum.getCuid() % 10 * 5 + 30}
																分鐘</span>
													</span>
													</span>
												</button>
											</h3>

											<svg aria-hidden="true" focusable="false"
												class="udlite-icon udlite-icon-small udlite-icon-color-neutral panel--expand-icon--1ZzXo">
												<use xlink:href="#icon-expand"></use></svg>
										</div>
									</div>

									<!-- hidden panel content wrapper (bootstrap collapse toggle) -->
									<div id="collapse${curriculum.getCuid()}"
										class="collapse panel--content-wrapper--1g5eE">
										<!-- hidden panel content -->
										<div
											class="udlite-accordion-panel-content panel--content--2q9WW">
											<ul class="unstyled-list udlite-block-list">
												<c:forEach var="x" begin="1"
													end="${curriculum.getCuid() % 10 + 2}">
													<li>
														<!-- section preview item -->
														<div
															class="udlite-block-list-item udlite-block-list-item-small udlite-text-sm">
															<svg aria-hidden="true" focusable="false"
																class="udlite-icon udlite-icon-xsmall udlite-icon-color-neutral udlite-block-list-item-icon">
																<use xlink:href="#icon-play"></use></svg>

															<!-- section preview item content -->
															<div class="udlite-block-list-item-content">
																<div>
																	<div class="section--row--3PNBT">
																		<span>第 ${x} 小節</span>
																	</div>
																</div>

																<span class="section--hidden-on-mobile--171Q9"
																	style="flex: 1 1 0%;"></span> <span
																	class="section--hidden-on-mobile--171Q9 section--item-content-summary--126oS">${curriculum.getCuid() % x + 11}:00</span>
															</div>
														</div>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>

				<c:set var="re" value="\\d+\\." />
				<c:if
					test="${not empty classBean.getClassDetailsBean().getStu_required().trim().split(re)}">
					<!-- "要求" block -->
					<div class="component-margin">
						<h2 class="udlite-heading-xl requirements--title--2j7S2"
							style="margin-bottom: 1.6rem;">要求</h2>
						<ul class="unstyled-list udlite-block-list">
							<c:forEach var="require"
								items="${classBean.getClassDetailsBean().getStu_required().trim().split(re)}">
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
											style="min-height: 1.96rem; margin-left: 1.6rem;">0 則評論</div>
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
											style="min-height: 1.96rem; margin-left: 1.6rem;">0 位學生</div>
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
											style="min-height: 1.96rem; margin-left: 1.6rem;">0 門課程</div>
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

	<!-- End #main -->
	<!-- ${classBean.getPhoto()} ${classBean.getClassDetailsBean().getGoal()}
  ${classBean.getClassDetailsBean().getStu_required()} ${classBean.getClassDetailsBean().getNeeded_tool()}-->

	<!-- courseComment -->
	<jsp:include page="../comment/courseComment.jsp" />

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<!-- Toast -->
	<jsp:include page="../article/toast.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

	<!-- moment js，用來轉換前端較好看的顯示時間 -->
	<script src="/SpecialTopic/assets/vendor/moment-with-locales.min.js"></script>

	<!-- Base js -->
	<script src="/SpecialTopic/js/base.js"></script>

	<!-- course-comment js -->
	<script src="/SpecialTopic/js/course-comment.js"></script>

	<svg style="position: absolute; width: 0; height: 0; overflow: hidden;"
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
<sec:authorize access="isAuthenticated()">
<script>
	$.ajax({
		type: "GET",
		url: "/SpecialTopic/checkOwned/${classBean.getCid()}",
		success: function(data) {
			if (data == "true") {
				$('button#sc_btn_${classBean.getCid()}').remove();
				$('div#add-to-cart').append("<h3>課程已購買</h3>");
				$('span.money-back').remove();
			}
		}
	})
</script>
</sec:authorize>
</body>

</html>