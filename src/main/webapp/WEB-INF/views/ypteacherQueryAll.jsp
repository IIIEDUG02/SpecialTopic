<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YPteacherAll</title>
<link rel="stylesheet" href="/css/ordersystem.css">

</head>
<body>
	<table>
		<thead>
			<tr>
				<td>老師名字:</td>
				<td>工作:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="th" items="${YPteacherList}">
				<tr>
					<td style="border-top:1px solid #000"><c:out value="${th}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.getMemberInformation()}" /></td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</body>
</html>