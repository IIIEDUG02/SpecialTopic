<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>    
<%@page import="net.ddns.iiiedug02.model.beans.MemberBean"%>
<%
WebApplicationContext context =
    WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
String message = (String) session.getAttribute("message");
MemberBean adminBean = (MemberBean) context.getBean("admin");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= adminBean %>
</body>
</html>