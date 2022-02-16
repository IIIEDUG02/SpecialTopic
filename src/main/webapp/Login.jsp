<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
String message = (String) session.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.6.0.js"></script>

<title>登入</title>
</head>
<body>
<div class="mainbox">
<form action="/SpecialTopic/LoginAuth" method="post">
帳號：<input type="text" id="usernametext" name="username" value="nilm987521">
密碼：<input type="password" id="passwordtext" name="password" value="abcd1234">

<c:if test="${ not empty message }">
   <a style="color:red"><%= message %></a><br/>
</c:if>

<input type="submit" id="login" value="登入">
</form>
</div>
</body>
</html>