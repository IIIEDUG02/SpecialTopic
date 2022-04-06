<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
<link rel="stylesheet" href="/css/ordersystem.css">

</head>
<body>
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
					<td ><c:out value="" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getClassID()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getYear()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getYearAmount()}" /></td>						
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
		<button type="submit" >重新排序</button><span>${errors.resetmsg}</span>
	</form>
	

</body>
</html>