<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>OOXX線上教學平台</title>
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
  <jsp:include page="incloud/head-css.jsp" />
  
  <!-- jQuery -->
  <script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  <script src="/SpecialTopic/js/ypteacher-section.js"></script>	
  <script src="/SpecialTopic/js/mpteacher-section.js"></script>
  <script src="/SpecialTopic/js/ypclass-section.js"></script>


<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="/SpecialTopic/js/ypteacher-section.js"></script>
<script src="/SpecialTopic/js/mpteacher-section.js"></script>

<!-- =======================================================
  * Template Name: Mentor - v4.7.0
  * Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="incloud/header-section.jsp" />

	<!-- ======= Hero Section ======= -->
	<section id="hero">
		<div class="hero-container" data-aos="fade-in">
			<h1>歡迎來到 OOXX線上教學平台</h1>
			<h2>Elegant Bootstrap Template for Startups, Apps &amp; more...</h2>
			<img src="/SpecialTopic/assets/img/hero-bg.jpg" alt="Hero Imgs"
				data-aos="zoom-out" data-aos-delay="100">
		</div>
	</section>
	<!-- End Hero Section -->

	<main id="main">

		<!-- ======= About Section ======= -->
		<jsp:include page="incloud/about-section.jsp" />

		<!-- ======= Counts Section ======= -->
		<section id="counts" class="counts section-bg">
			<div class="container">

				<div class="row counters">

					<div class="col-lg-3 col-6 text-center">
						<span data-purecounter-start="0" data-purecounter-end="0"
							data-purecounter-duration="1" class="purecounter" id="countmember"></span>
						<p>位學生</p>
						<!-- ======= TODO:聰賢 ======= -->
					</div>
					<script src="/SpecialTopic/js/countmember.js"></script>
					<div class="col-lg-3 col-6 text-center">
						<span data-purecounter-start="0" data-purecounter-end="0"
							data-purecounter-duration="1" class="purecounter" id="classcount"></span>
						<p>門課程</p>
					</div>
					<script src="/SpecialTopic/js/count.js"></script>

					<div class="col-lg-3 col-6 text-center">
						<span data-purecounter-start="0" data-purecounter-end="42"
							data-purecounter-duration="1" class="purecounter"></span>
						<p>個活動</p>
						<!-- ======= TODO:？？？？ ======= -->
					</div>

					<div class="col-lg-3 col-6 text-center">
						<span data-purecounter-start="0" data-purecounter-end="15"
							data-purecounter-duration="1" class="purecounter"></span>
						<p>位老師</p>
						<!-- ======= TODO:聰賢 ======= -->
					</div>

				</div>

			</div>
		</section>
		<!-- End Counts Section -->

		<!-- ======= Why Us Section ======= -->
		<section id="why-us" class="why-us">
			<div class="container" data-aos="fade-up">

				<div class="row">
					<div class="col-lg-4 d-flex align-items-stretch">
						<div class="content">
							<h3>Why Choose Mentor?</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Duis aute irure dolor in reprehenderit Asperiores
								dolores sed et. Tenetur quia eos. Autem tempore quibusdam vel
								necessitatibus optio ad corporis.</p>
							<div class="text-center">
								<a href="about.html" class="more-btn">Learn More <i
									class="bx bx-chevron-right"></i></a>
							</div>
						</div>
					</div>
					<div class="col-lg-8 d-flex align-items-stretch" data-aos="zoom-in"
						data-aos-delay="100">
						<div class="icon-boxes d-flex flex-column justify-content-center">
							<div class="row">
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-receipt"></i>
										<h4>Corporis voluptates sit</h4>
										<p>Consequuntur sunt aut quasi enim aliquam quae harum
											pariatur laboris nisi ut aliquip</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-cube-alt"></i>
										<h4>Ullamco laboris ladore pan</h4>
										<p>Excepteur sint occaecat cupidatat non proident, sunt in
											culpa qui officia deserunt</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-images"></i>
										<h4>Labore consequatur</h4>
										<p>Aut suscipit aut cum nemo deleniti aut omnis. Doloribus
											ut maiores omnis facere</p>
									</div>
								</div>
							</div>
						</div>
						<!-- End .content-->
					</div>
				</div>

			</div>
		</section>
		<!-- End Why Us Section -->

		<!-- ======= Features Section ======= -->
		<section id="features" class="features">
			<div class="container" data-aos="fade-up">
				<!-- ======= TDDO: 課程標籤？？？？ ======= -->
				<div class="row" data-aos="zoom-in" data-aos-delay="100">
					<div class="col-lg-3 col-md-4">
						<div class="icon-box">
							<i class="ri-store-line" style="color: #ffbb2c;"></i>
							<h3>
								<a href="">Lorem Ipsum</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
						<div class="icon-box">
							<i class="ri-bar-chart-box-line" style="color: #5578ff;"></i>
							<h3>
								<a href="">Dolor Sitema</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4 mt-md-0">
						<div class="icon-box">
							<i class="ri-calendar-todo-line" style="color: #e80368;"></i>
							<h3>
								<a href="">Sed perspiciatis</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4 mt-lg-0">
						<div class="icon-box">
							<i class="ri-paint-brush-line" style="color: #e361ff;"></i>
							<h3>
								<a href="">Magni Dolores</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-database-2-line" style="color: #47aeff;"></i>
							<h3>
								<a href="">Nemo Enim</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-gradienter-line" style="color: #ffa76e;"></i>
							<h3>
								<a href="">Eiusmod Tempor</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-file-list-3-line" style="color: #11dbcf;"></i>
							<h3>
								<a href="">Midela Teren</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-price-tag-2-line" style="color: #4233ff;"></i>
							<h3>
								<a href="">Pira Neve</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-anchor-line" style="color: #b2904f;"></i>
							<h3>
								<a href="">Dirada Pack</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-disc-line" style="color: #b20969;"></i>
							<h3>
								<a href="">Moton Ideal</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-base-station-line" style="color: #ff5828;"></i>
							<h3>
								<a href="">Verdo Park</a>
							</h3>
						</div>
					</div>
					<div class="col-lg-3 col-md-4 mt-4">
						<div class="icon-box">
							<i class="ri-fingerprint-line" style="color: #29cc61;"></i>
							<h3>
								<a href="">Flavor Nivelanda</a>
							</h3>
						</div>
					</div>
				</div>
				<!-- ======= END:TDDO ======= -->
			</div>
		</section>
		<!-- End Features Section -->

		<!-- ======= Popular Courses Section ======= -->
		<section id="popular-courses" class="courses">
			<div class="container" data-aos="fade-up">

				<div class="section-title">
					<h2>Courses</h2>
					<p>熱門課程</p>
				</div>
				<!-- ======= TODO：宥竣 ======= -->
				<div class="row" data-aos="zoom-in" data-aos-delay="100">

					<div class="col-lg-4 col-md-6 d-flex align-items-stretch">
						<div class="course-item">
							<img src="assets/img/course-1.jpg" class="img-fluid" alt="...">
							<div class="course-content">
								<div
									class="d-flex justify-content-between align-items-center mb-3">
									<h4>Web Development</h4>
									<p class="price">$169</p>
								</div>

								<h3>
									<a href="course-details.html">Website Design</a>
								</h3>
								<p>Et architecto provident deleniti facere repellat nobis
									iste. Id facere quia quae dolores dolorem tempore.</p>
								<div
									class="trainer d-flex justify-content-between align-items-center">
									<div class="trainer-profile d-flex align-items-center">
										<img src="assets/img/trainers/trainer-1.jpg" class="img-fluid"
											alt=""> <span>Antonio</span>
									</div>
									<div class="trainer-rank d-flex align-items-center">
										<i class="bx bx-user"></i>&nbsp;50 &nbsp;&nbsp; <i
											class="bx bx-heart"></i>&nbsp;65
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End Course Item-->

					<div
						class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-md-0">
						<div class="course-item">
							<img src="assets/img/course-2.jpg" class="img-fluid" alt="...">
							<div class="course-content">
								<div
									class="d-flex justify-content-between align-items-center mb-3">
									<h4>Marketing</h4>
									<p class="price">$250</p>
								</div>

								<h3>
									<a href="course-details.html">Search Engine Optimization</a>
								</h3>
								<p>Et architecto provident deleniti facere repellat nobis
									iste. Id facere quia quae dolores dolorem tempore.</p>
								<div
									class="trainer d-flex justify-content-between align-items-center">
									<div class="trainer-profile d-flex align-items-center">
										<img src="assets/img/trainers/trainer-2.jpg" class="img-fluid"
											alt=""> <span>Lana</span>
									</div>
									<div class="trainer-rank d-flex align-items-center">
										<i class="bx bx-user"></i>&nbsp;35 &nbsp;&nbsp; <i
											class="bx bx-heart"></i>&nbsp;42
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End Course Item-->


					<div
						class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-lg-0">
						<div class="course-item">
							<img src="assets/img/course-3.jpg" class="img-fluid" alt="...">
							<div class="course-content">
								<div
									class="d-flex justify-content-between align-items-center mb-3">
									<h4>Content</h4>
									<p class="price">$180</p>
								</div>

								<h3>
									<a href="course-details.html">Copywriting</a>
								</h3>
								<p>Et architecto provident deleniti facere repellat nobis
									iste. Id facere quia quae dolores dolorem tempore.</p>
								<div
									class="trainer d-flex justify-content-between align-items-center">
									<div class="trainer-profile d-flex align-items-center">
										<img src="assets/img/trainers/trainer-3.jpg" class="img-fluid"
											alt=""> <span>Brandon</span>
									</div>
									<div class="trainer-rank d-flex align-items-center">
										<i class="bx bx-user"></i>&nbsp;20 &nbsp;&nbsp; <i
											class="bx bx-heart"></i>&nbsp;85
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End Course Item-->
					<!-- ======= END:TODO ======= -->
				</div>


			</div>
		</section>
		<!-- End Popular Courses Section -->


    <!-- ======= Trainers Section ======= -->
    <jsp:include page="incloud/trainers-section.jsp" />
    <jsp:include page="incloud/class-section.jsp" />


	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<jsp:include page="incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="incloud/body-js.jsp" />

	<!-- ======= errMsg ======= -->
	<c:if test="${not empty errMsg}">
		<script>alert("${errMsg}")</script>
	</c:if>
</body>

</html>