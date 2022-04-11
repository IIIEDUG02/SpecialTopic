<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../incloud/head-css.jsp" />
<title>MainPercentInput</title>
<link href="/SpecialTopic/css/jquery-ui.css" rel="stylesheet" />
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="/SpecialTopic/js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
.width{
	width: 600px;
}
.table-striped>tbody>tr:nth-child(odd)>td, 
.table-striped>tbody>tr:nth-child(odd)>th {
   background-color: rgb(210, 244, 224   ); 
 }

</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
			<div>
					<table  align="center" class="width table table-striped">
						<tr>
							<td align="center" style="border-bottom:5px solid #000">課程ID:</td>
							<td align="center" style="border-bottom:5px solid #000">課程名稱:</td>
							<td align="center" style="border-bottom:5px solid #000">課程種類:</td>
							<td align="center" style="border-bottom:5px solid #000">訂單數量:</td>
						</tr>
						<c:forEach var="ca" items="${classList}">
							<tr>
								<td align="center"><c:out value="${ca.get('cid')}" /></td>				
								<td align="center"><c:out value="${ca.get('title')}" /></td>				
								<td align="center"><c:out value="${ca.get('class_type')}" /></td>							
								<td align="center"><c:out value="共${ca.get('count')}筆" /></td>							
							</tr>
						</c:forEach>
					</table>
			</div >			
				<div id="tabs" class="width" >
					<ul>
						<li><a href="#p1">學員工作統計</a></li>
						<li><a href="#p2">學員性別統計</a></li>
						<li><a href="#p3">學員年齡統計</a></li>
					</ul>
					<div id="p1">
						<form action="getJobPercentbyID" method="get">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input  class="form-control" placeholder="請輸入ID" type="text" name="id" /></td>
									<td>${errors.jobid}</td>
								</tr>
								<tr>
									<td><button type="submit" value="login">送出</button></td>
									<td>${errors.jobmsg}</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="p2">
						<form action="getgenderbyID" method="get">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input class="form-control" placeholder="請輸入ID" type="text" name="id" /></td>
									<td>${errors.genderid}</td>
								</tr>
								<tr>
									<td><button type="submit" value="login">送出</button></td>
									<td>${errors.gendermsg}</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="p3">
						<form action="getAgePercentbyID" method="get">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input class="form-control" placeholder="請輸入ID" type="text" name="id" /></td>
									<td>${errors.ageid}</td>
								</tr>
								<tr>
									<td><button type="submit" style="background-color:#92F9BC" value="login">送出</button></td>
									<td>${errors.agemsg}</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../incloud/footer-section.jsp" />

				<div id="preloader"></div>
				<a href="#"
					class="back-to-top d-flex align-items-center justify-content-center"><i
					class="bi bi-arrow-up-short"></i></a>

				<!-- Templete JS -->
				<jsp:include page="../incloud/body-js.jsp" />
</body>
</html>