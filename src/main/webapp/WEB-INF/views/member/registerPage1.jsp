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
	margin: 10px;
}

/* <style>.main-box { */
/* 	margin: 50px auto; */
/* 	width: 400px; */
/* 	height: 570px; */
/* 	padding: 50px; */
/* 	box-shadow: 5px 5px 10px #999; */
/* 	border: 1px solid #fff text-align:center; */
/* 	font-size: 1.0em; */
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
				<div class="header1">
					<h3 style="text-align: left">帳號註冊</h3>
				</div>
				<form action="/SpecialTopic/registerAction1" id="registerform"
					METHOD="POST">
					<div class="form-group1">
						<input type="text" id="username" name="username" class="username"
							placeholder="帳號:" required="required" maxlength="16" />
					</div>
					<div class="form-group1">
						<input type="password" id="password" name="password"
							class="password" placeholder="密碼:" required="required"
							maxlength="20" />
					</div>
					<div class="form-group1">
						<input type="password" id="repassword" name="repassword"
							class="repassword" required="required" maxlength="20"
							placeholder="確認密碼:" />
					</div>
					<div class="form-group1">
						<input type="text" name="fullname" required="required"
							class="fullname" placeholder="姓名:" />
					</div>

					<div class="form-group1">
						<input type="text" name="email" id="email" required="required"
							class="email" placeholder="電子信箱:" />
					</div>

					<div class="form-group1">
						<input type="text" name="address" required="required"
							class="address" placeholder="地址:" />
					</div>

					<div class="form-group1">
						<input type="text" name="phone" id="phone" required="required"
							class="phone" placeholder="手機:" />
					</div>

					<div class="form-group1">
						生日:<input type="date" name="birthday" required="required" />
					</div>

					<div class="form-group1">
						<input type="text" name="job" required="required" class="job"
							placeholder="工作:" />
					</div>

					<div class="form-group1">
						<input type="text" id="identitycard" name="identitycard"
							required="required" class="identitycard" placeholder="身分證字號:" />
					</div>

					<div class="form-group1">
						<input type="text" name="passportname" required="required"
							class="address" class="passportname" placeholder="護照英文名子:" />
					</div>

					<div class="form-group1">
						性別: 男<input type="radio" name="gender" value=1 />女<input
							type="radio" name="gender" value=0 />
					</div>

					<div class="form-group1">
						<input type="button" value="送 出" onclick="check()" /> <input
							type="button"
							onclick="javascript:window.location = '/SpecialTopic/'"
							value="返 回 首 頁" />
					</div>
				</form>
			</div>
			<div class="col"><img src="/SpecialTopic/img/register/littletree.jpg"></div>
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