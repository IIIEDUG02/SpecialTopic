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
<style>
.position_fixed {
	position: fixed;
}
#classdiv{
background-color:white
}
#courses{
background-color:rgb(243, 243, 241)
}
div#header-title{
background-color:#5FCF80	;
border-radius: 30px;

}

.height100 {
	height: 100px;
}
</style>

<!-- Favicons -->
<jsp:include page="incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="incloud/head-css.jsp" />



<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

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
	<div id="header-title" class="container p-2 ">
	<h2 class="m-0 ms-2 ">請自由探索喜歡課程</h2>
	</div>
		<!-- ======= Courses Section ======= -->
		<section id="courses" class="courses">

			<div class="container" data-aos="fade-up">


				<div id="row" class="row m-3" data-aos="zoom-in" data-aos-delay="100">
				<c:choose>
				<c:when test="${empty classOneTypeList}">
				<h3>尚無此類型課程</h3>
				</c:when>
				</c:choose>
					<div class="col-lg-12 flex-wrap d-flex align-items-stretch">
						
						<c:forEach items="${classOneTypeList}" var="cb">
							<div  id="classdiv" class="course-item m-4  col-3 shadow">
								<img id="classphoto" src="https://iiiedug02.nilm.in${cb.getPhoto()}" class="img-fluid rounded" alt="...">
								
								<div class="course-content">
									<div
										class="d-flex justify-content-between align-items-center mb-3">
										<h4>${cb.getClassType()}</h4>
										<p class="price">NT$${cb.getPrice()}</p>
									</div>

									<h3>
										<a href="/SpecialTopic/viewClass/${cb.getCid()}">${cb.getTitle()}</a>
									</h3>
									<p>${cb.getClassDetailsBean().getDescript()}</p>
<!--  									<div
										class="trainer d-flex justify-content-between align-items-center">
										<div class="trainer-profile d-flex align-items-center">
											<img src="assets/img/trainers/trainer-1.jpg"
												class="img-fluid" alt=""> <span>Antonio</span>
										</div>
										<div class="trainer-rank d-flex align-items-center">
											<i class="bx bx-user"></i>&nbsp;50 &nbsp;&nbsp; <i
												class="bx bx-heart"></i>&nbsp;65
										</div>  
									</div> -->
								</div>
							</div>
						</c:forEach>


					</div>
				</div>
			</div>

</section>
	</main>
	<!-- End #main -->
	<div class="height100"></div>
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