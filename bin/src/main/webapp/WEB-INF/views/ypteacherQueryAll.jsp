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
				<td>老師ID:</td>
				<td>地址:</td>
				<td>老師電話:</td>
				<td>老師名字:</td>
				<td>老師email:</td>
				<td>老師生日:</td>
				<td>老師工作:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="meminfo" items="${memberinfoList}">
				<tr>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getUid()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getAddress()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getPhone()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getFullname()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getEmail()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getBirthday()}" /></td>
					<td style="border-top:1px solid #000"><c:out value="${meminfo.getJob()}" /></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</body>
</html>