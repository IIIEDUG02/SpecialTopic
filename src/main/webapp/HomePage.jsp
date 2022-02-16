<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String username = (String) session.getAttribute("username");
if (null == username) {
  response.sendRedirect("Login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入後</title>
</head>
<body>
Hello <%= username %>
<form action="Logout" method="get">
<button type="submit">登出</button>
</form>

</body>
</html>