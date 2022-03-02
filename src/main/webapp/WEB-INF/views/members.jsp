<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Members</h3>
<form:form action="addMembers" mehtod="POST" modelAttribute="Mbs">
<table>
	<tr>
		<td><form:label path="name">名字</form:label></td>
		<td><form:input path="name"/></td>
	</tr>
	<tr>
		<td><form:label path="gender">性別</form:label></td>
		<td><form:input path="gender"/></td>
	</tr>
	<tr>
		<td><form:label path="age">年齡</form:label></td>
		<td><form:input path="age"/></td>
	</tr>
	<tr>
		<td colspan="2"><form:button value="send">送出</form:button></td>

	</tr>
</table>


</form:form>


</body>
</html>