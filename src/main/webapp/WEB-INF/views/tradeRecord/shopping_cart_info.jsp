<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<title>購物車清單</title>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
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

<!-- 購物車的JS -->
<script src="/SpecialTopic/js/shopping_cart.js"></script>

<style>
	body {
		font-family: "PingFang TC", 微軟正黑體, sans-serif;
	}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs" data-aos="fade-in"></div>
	<!-- End Breadcrumbs -->

	<!-- main start -->
	<div class="container  min-vh-100">
		<div class="row">
			<div class="col-sm-1"></div>
			<div id="sc_info" class="col-sm-8 mt-5">
				<h4 class="">
					<span aria-hidden="true" class="fa fa-shopping-cart"></span> 購物車 <small
						class="text-muted">總計 <a id="total">${shoppingCartList.size()}</a> 堂課</small>
				</h4>
				<div class="row m-3 p-1">
					<div class="col-6">
						<a>課程名稱:</a>
					</div>
					<div class="col-6">
						<a>價格:</a>
					</div>
				</div>
				<hr class="border-2 border-top border-black">
				<c:forEach var="item" items="${shoppingCartList}">
					<div id="item${item.getId()}" class="row m-3 shadow p-1">
						<div class="col-6">
							<h4>${item.getClassBean().getTitle()}</h4>
						</div>
						<div class="col-4">
							<h4 id="price${item.getId()}">${item.getClassBean().getPrice()}</h4>
						</div>
						<div class="col-2">
							<button type="button" class="btn btn-danger"
								onclick="sc_del(${item.getId()})">刪除</button>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="col-sm-3 mt-5 mb-3">
				<div class="shadow p-3">
					<h5 class="mt-3">訂單明細</h5>
					<hr class="border-2 border-top border-black">
					<div>小記</div>
					<h3 class="text-end">新台幣：<a id="sum">${ sum }</a></h3>
					<div class="text-end">
						<button type="button" class="btn btn-success">結帳</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />


	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

</body>
</html>