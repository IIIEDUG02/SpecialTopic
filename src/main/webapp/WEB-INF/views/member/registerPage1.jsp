<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>帳號註冊</title>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="keywords">

<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/registerjs.js"></script>

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}

form .username, form .password, form .repassword, form .fullname, form .email,
	form .address, form .phone, form .job, form .identitycard {
	border-radius: 10px;
	margin: 30px;
}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>

	<div class="container">
		<div class="row">
			<div class="col">
				<img src="/SpecialTopic/img/register/littletree.jpg">
			</div>
			<div class="col">
				<div class="header1">
					<h3 style="text-align: left">帳號註冊</h3>
				</div>
				<form action="/SpecialTopic/registerAction1" id="registerform"
					METHOD="POST">
					<div class="form-group1">
						帳號:<input type="text"
							onkeyup="this.value=this.value.replace(/\s+/g,'')" id="username"
							name="username" class="username" placeholder="帳號:"
							required="required" maxlength="20" />
							<a>英文+數字不超過20字</a>
					</div>
					<div class="form-group1">
						密碼:<input type="password" id="password" name="password"
							onkeyup="this.value=this.value.replace(/\s+/g,'')"
							class="password" placeholder="密碼:" required="required"
							maxlength="20" />
					</div>
					<div class="form-group1">
						確認密碼:<input type="password" id="repassword" name="repassword"
							onkeyup="this.value=this.value.replace(/\s+/g,'')"
							class="repassword" required="required" maxlength="20"
							placeholder="確認密碼:" />
					</div>
					<div class="form-group1">
						電子信箱:<input type="text"
							onkeyup="this.value=this.value.replace(/\s+/g,'')" name="email"
							id="email" required="required" class="email" placeholder="電子信箱:" />
					</div>


					<div class="form-group1">
						<input type="button" value="送 出" onclick="check()" /> <input
							type="button" style="margin-left: 160px"
							onclick="javascript:window.location = '/SpecialTopic/'"
							value="返 回 首 頁" />
					</div>
				</form>
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