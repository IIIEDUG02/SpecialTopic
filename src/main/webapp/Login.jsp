<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
帳號：<input type="text" id="usernametext" name="username" value="nilm987521"><br>
密碼：<input type="password" id="passwordtext" name="password" value="abcd1234"><br>
<% if (message != null) { %>
  <a style="color:red"><%= message %></a><br/>
<% }%>
<input type="submit" id="login" value="登入">
</div>
</form>
</body>
</html>