<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page import="net.ddns.iiiedug02.model.services.MemberService" %>
<%@ page import="net.ddns.iiiedug02.model.beans.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
MemberService ms = context.getBean("memberService", MemberService.class);

String username = (String) session.getAttribute("username");
String auth = (String) session.getAttribute("auth");
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
<div>
Hello <%= username %> <c:if test="${ auth == 'admin' }">(系統管理者)</c:if> <c:if test="${ auth == 'nuser' }">(一般使用者)</c:if>
<button type="submit" onclick="javascript:location.href='Logout'">登出</button>
</div>

<% 

List<MemberBean> mbs = ms.selectAll();
%>
<Table>
<thead>
<tr>
<td>帳號</td>
<td>密碼</td>
<td>權限</td>
<td>狀態</td>
<td>住址</td>
<td>電話</td>
<td>操作</td>
</tr>
</thead>
<tbody>
<% 
for (MemberBean mb: mbs) {%>
<tr>
<td><%= mb.getUsername() %></td>
<td><%= mb.getPassword() %></td>
<td><%= mb.getAuth() %></td>
<td><%= mb.getActivated() == 0 ? "啟用" : "停用" %></td>
<td><%= mb.getMemberDetail().getAddress() %></td>
<td><%= mb.getMemberDetail().getPhone() %></td>
<td>
<% if (mb.getAuth() == "admin") { %>
<input type="button" onclick="javascript:location.href='DeleteMember?mid=<%= mb.getUsername() %>'" value="刪除">
<% } %>
<input type="button" onclick="javascript:location.href='?mid=<%= mb.getUsername() %>'" value="修改">
</td>
</tr>
<% } %>
</tbody>
</Table>

</body>
</html>