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

<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />

<style>
body {
	font-family: "PingFang TC", 微軟正黑體, sans-serif;
	font-size: 16px;
	color: rgba(0, 0, 0, 0.65);
	margin: 10px;
	padding: 0px;
	min-height: 100vh;
}
div#title{

}

label {
	margin-top: 15px;
	margin-bottom: 10px;
}
.btn{
margin-top: 20px;
}

.input-wrap {
	min-width: 380px;
	margin: 10px;
	background-color: rgb(243, 243, 241);
	margin-bottom: 60px;
	padding: 15px 30px 45px;
	border-radius: 50px;
}

img {
	margin-top: 20px;
}
</style>

</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	
	<div class="container ">
		<div id="title" class="shawdow row " style="text-align:center"><h3><strong>課程標題</strong></h3></div>
		<div class="shawdow input-wrap">
			
			<form action="createclassdetails" METHOD="POST">
				<div class="form-group">
					<label for="descript">課程描述</label> <textarea
						class="form-control" id="descript" name="descript"
						placeholder="請輸入文字"></textarea>
				</div>
				<div class="form-group">
					<label for="needed_tool">自備工具</label> <textarea
						class="form-control" id="needed_tool" name="needed_tool"
						placeholder="請輸入文字"></textarea>
				</div>
				<div class="form-group">
					<label for="stu_required">學生要求</label> <textarea
						class="form-control" id="stu_required" name="stu_required"
						placeholder="請輸入文字"></textarea>
				</div>
				<div class="form-group">
					<label for="length_min">影片長度(分鐘)</label> <input type="text"
						class="form-control" id="length_min" name="length_min"
						placeholder="請輸入影片長度">
				</div>
				<div class="form-group">
					<label for="goal">目標</label> <textarea 
						class="form-control" id="goal" name="goal"
						placeholder="請輸入文字"></textarea>
				</div>
				<div class="form-group">
					<label for="video">影片連結</label> <input type="text"
						class="form-control" id="video" name="video"
						placeholder="請輸入影片連結">
				</div>
				<div  style="text-align:center">
				<input type="submit" class="btn btn-primary" value="完成" /> 
				</div>
			</form>

		</div>
	</div>
	

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />


</body>
</html>