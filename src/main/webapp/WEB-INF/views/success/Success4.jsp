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
				<td>資料成功存入:</td>
				<td>課程ID:</td>
				<td>月份:</td>
				<td>數量:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="th" items="${mpclassList}">
				<tr>
					<td ><c:out value="" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getClassID()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getMonth()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getMonthAmount()}" /></td>						
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</body>
</html>