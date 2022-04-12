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

<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />

</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	
	
	<h3>課程創建</h3>
	<form action="createclassdetails" METHOD="POST" >
		<table>
			<tr>
				<td><label>描述:</label><input type="text" id="descript"
					name="descript" /></td>
			</tr>
			<tr>
				<td><label>自備工具:</label><input type="text" id="needed_tool"
					name="needed_tool" /></td>
			</tr>
			<tr>
				<td><label>學生要求:</label><input type="text" id="stu_required"
					name="stu_required" /></td>
			</tr>
			<tr>
				<td><label>影片長度:</label><input type="text" id="length_min"
					name="length_min" /></td>
			</tr>
			<tr>
				<td><label>目標:</label><input type="text" id="goal"
					name="goal" /></td>
			</tr>
			<tr>
				<td><label>影片連結:</label><input type="text" id="video"
					name="video" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="下一步" /> <input type="button"
					onclick="javascript:window.location = '/'" value="返回首頁" /></td>
			</tr>
		</table>
	</form>
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