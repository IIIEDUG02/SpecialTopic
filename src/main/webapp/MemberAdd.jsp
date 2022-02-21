<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.ddns.iiiedug02.model.beans.MemberBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String message = (String) request.getSession(false).getAttribute("message");
MemberBean SignBena = (MemberBean) request.getSession(false).getAttribute("SignBean");
session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp" flush="true" />
	<form action="SignUp" method="post">
		帳號： <input id="" name="username" type="text" 
			value=<%= SignBena == null ? "nilm987521" : SignBena.getUsername() %>>
			<%
			if (message != null) { %> <a style="color:red"><%= message %></a> <% }%>
			<br>
		密碼： <input id="" name="password" type="password" 
			value=<%= SignBena == null ? "abcd1234" : SignBena.getPassword() %>><br>
		密碼確認：<input id="" name="password2" type="password" 
			value=<%= SignBena == null ? "abcd1234" : SignBena.getPassword() %>><br>
		姓名： <input id="" name="fullname" type="text" 
			value=<%= SignBena == null ? "藍翊家" : SignBena.getMemberDetail().getFullname() %>><br>
		E-mail： <input id="" name="email" type="text"
			value=<%= SignBena == null ? "nilm987521@gmail.com" : SignBena.getMemberDetail().getEmail() %>><br> 
		生日： <input id="" name="birthday" type="date" 
			value=<%= SignBena == null ? "1991-08-30" : SignBena.getMemberDetail().getBirthday() %>><br> 
		地址： <input id="" name="address" type="text" 
			value=<%= SignBena == null ? "台灣是寶島" : SignBena.getMemberDetail().getAddress() %>><br>
		電話： <input id="" name="phone" type="text" 
			value=<%= SignBena == null ? "09872XX3XX" : SignBena.getMemberDetail().getPhone() %>><br>
		職業： <input id="" name="job" type="text" 
			value=<%= SignBena == null ? "程序猿、工程獅" : SignBena.getMemberDetail().getJob() %>><br>
		<button type="submit">註冊</button>
		<button onclick="javascript:history.back()">放棄</button>
	</form>
</body>
</html>