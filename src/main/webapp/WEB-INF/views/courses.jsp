<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Courses - Mentor Bootstrap Template</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<jsp:include page="incloud/favicons.jsp" />

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Head CSS -->
<jsp:include page="incloud/head-css.jsp" />

<!-- Head js -->
<jsp:include page="incloud/head-js.jsp" />
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
	<div class="height100"></div>

	<main id="main" data-aos="fade-in">

		<!-- ======= Courses Section ======= -->
		<section id="courses" class="courses">

			<div class="container" data-aos="fade-up">
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton1" data-bs-toggle="dropdown"
						aria-expanded="false">Dropdown button</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<c:forEach var="classtype" items="${classTypeList}">
								<li><a class="dropdown-item" href="#">${classtype}</a></li>							
						</c:forEach>
						
					</ul>
				</div>


				<div id="row" class="row" data-aos="zoom-in" data-aos-delay="100">
					<div class="col" id="loadingCircle">
						<div
							class="spinner-border text-success d-flex justify-content-center"
							role="status" style="width: 10rem; height: 10rem;">
							<span class="sr-only">Loading...</span>
						</div>
					</div>

					<script src="/SpecialTopic/js/class.js"></script>
					<!--           <div class="col-lg-4 col-md-6 d-flex align-items-stretch"> -->
					<!--             <div class="course-item"> -->
					<!--               <img src="assets/img/course-1.jpg" class="img-fluid" alt="..."> -->
					<!--               <div class="course-content"> -->
					<!--                 <div class="d-flex justify-content-between align-items-center mb-3"> -->
					<!--                   <h4>Web Development</h4> -->
					<!--                   <p class="price">$169</p> -->
					<!--                 </div> -->

					<!--                 <h3><a href="course-details.html">Website Design</a></h3> -->
					<!--                 <p>Et architecto provident deleniti facere repellat nobis iste. Id facere quia quae dolores dolorem tempore.</p> -->
					<!--                 <div class="trainer d-flex justify-content-between align-items-center"> -->
					<!--                   <div class="trainer-profile d-flex align-items-center"> -->
					<!--                     <img src="assets/img/trainers/trainer-1.jpg" class="img-fluid" alt=""> -->
					<!--                     <span>Antonio</span> -->
					<!--                   </div> -->
					<!--                   <div class="trainer-rank d-flex align-items-center"> -->
					<!--                     <i class="bx bx-user"></i>&nbsp;50 -->
					<!--                     &nbsp;&nbsp; -->
					<!--                     <i class="bx bx-heart"></i>&nbsp;65 -->
					<!--                   </div> -->
					<!--                 </div> -->
					<!--               </div> -->
					<!--             </div> -->
					<!--           </div> End Course Item -->

				</div>
			</div>
		</section>
		<!-- End Courses Section -->

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

</body>

</html>