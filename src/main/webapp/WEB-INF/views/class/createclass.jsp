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
<jsp:include page="../incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<style>
body {
	font-family: "PingFang TC", 微軟正黑體, sans-serif;
	font-size: 16px;
	color: rgba(0, 0, 0, 0.65);
	background-color: rgb(243, 243, 241);
	margin: 10px;
	padding: 0px;
	min-height: 100vh;
}

label {
	margin-top: 15px;
	margin-bottom: 10px;
}

.input-wrap {
	min-width: 380px;
	margin: 10px;
	background-color: white;
	margin-bottom: 60px;
	padding: 15px 30px 45px;
}

img {
	margin-top: 20px;
}
</style>
<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>

	<div class="container ">
		<div class="shawdow row " style="text-align:center"><h3>課程標題</h3></div>
		<div class="shawdow input-wrap">
			
			<form action="createclass" METHOD="POST"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="classtitle">課程標題</label> <input type="text"
						class="form-control" id="classtitle" name="classtitle"
						placeholder="請輸入文字">
				</div>
				<div class="form-group">
					<label for="classtype">課程類型</label> <input type="text"
						class="form-control" id="classtype" name="classtype"
						placeholder="請輸入文字">
				</div>
				<div class="form-group">
					<label for="classprice">價錢</label> <input type="text"
						class="form-control" id="classprice" name="classprice"
						placeholder="請輸入文字">
				</div>
				<div class="form-group">
					<label for="classprice">老師ID</label> <input type="text"
						class="form-control" id="teacherid" name="teacherid"
						placeholder="請輸入ID">
				</div>
				<div class="form-group row align-items-center justify-content-center">
					<div class="col">
						<label for="photopath">課程圖片</label> <input type="file"
							class="form-control" id="photopath" name="photopath"
							placeholder="課程圖片">
					</div>
					<div class="col">
						<img id="preview_img" src="img/class/defaultclass.jpg" alt="預覽圖片"
							style="width: 260px; height: 200px ; border:solid"/>
					</div>
				</div>


				<input type="submit" class="btn btn-primary" value="下一步" /> <input
					type="button" class="btn btn-primary"
					onclick="javascript:window.location = '/'" value="返回首頁" />

			</form>

		</div>
	</div>



	<script>
$("#photopath").change(function(){
	$("#preview_img").attr('src', "");
  readURL(this);
});
function readURL(input){
  if(input.files && input.files[0]){
    var reader = new FileReader();
    reader.onload = function (e) {
       $("#preview_img").attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>
	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

	<!-- ======= errMsg ======= -->
	<c:if test="${not empty msg}">
		<script>alert("${msg}")</script>
	</c:if>
</body>
</html>