<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../incloud/head-css.jsp" />
<jsp:include page="../incloud/head-css.jsp" />
<title>Success</title>
<link rel="stylesheet" href="/css/ordersystem.css">
<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
				<table>
					<thead>
						<tr>
							<td>資料成功載入:</td>
							<td>課程ID:</td>
							<td>年份:</td>
							<td>數量:</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="th" items="${ypclasscontroll}">
							<tr>
								<td><c:out value="" /></td>
								<td style="border-top: 1px solid #000"><c:out
										value="${th.getClassID()}" /></td>
								<td style="border-top: 1px solid #000"><c:out
										value="${th.getYear()}年" /></td>
								<td style="border-top: 1px solid #000"><c:out
										value="共${th.getYearAmount()}筆" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form action="ypclasschangetop5" method="get">
					<table>
						<tr>
							<td>輸入第一順位:</td>
							<td><input type="text" name="pi1" /></td>
							<td>${errors.pi1}</td>
						</tr>
						<tr>
							<td>輸入第二順位:</td>
							<td><input type="text" name="pi2" /></td>
							<td>${errors.pi2}</td>
						</tr>
						<tr>
							<td>輸入第三順位:</td>
							<td><input type="text" name="pi3" /></td>
							<td>${errors.pi3}</td>
						</tr>
						<tr>
							<td><button type="submit">送出</button></td>
							<td>${errors.pimsg}</td>
						</tr>
					</table>
				</form>
				<form action="resetypclass" method="get">
					<button type="submit">重新排序</button>
					<span>${errors.resetmsg}</span>
				</form>
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