<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.jasypt.encryption.pbe.StandardPBEStringEncryptor"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="net.ddns.iiiedug02.model.services.MemberService"%>
<%@page import="net.ddns.iiiedug02.model.beans.MemberBean"%>
<%
WebApplicationContext context =
    WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
StandardPBEStringEncryptor strongEncryptor =
    (StandardPBEStringEncryptor) context.getBean("strongEncryptor");
MemberService memberService = (MemberService) context.getBean("memberService");
String message = (String) session.getAttribute("message");
Cookie[] cookies = request.getCookies();
if (cookies != null) {
  for (Cookie cookie : cookies) {
    if ((cookie.getName()).equals("username")) {
	  String username = strongEncryptor.decrypt(cookie.getValue());
	  MemberBean cookieBean = memberService.selectByUsername(username);
	  if (cookieBean != null) {
	    session.setAttribute("loginBean", cookieBean);
	    response.sendRedirect("/SpecialTopic/MemberFunction/MemberInfo.jsp");
	  }
    }
  }
}
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
			帳號：<input type="text" id="usernametext" name="username"
				value="nilm987521"><br /> 密碼：<input type="password"
				id="passwordtext" name="password" value="abcd1234"><br />

			<c:if test="${ not empty message }">
				<a style="color: red"><%=message%></a>
				<br />
			</c:if>
			<!-- TODO: 改成用js驗證資料完整性後再送出 -->
			自動登入<input type="checkbox" name="autoLogin"> <input
				type="submit" id="login" value="登入" /> <input type="button"
				id="signup" value="註冊"
				onclick="javascript:location.href='MemberAdd.jsp'" />
		</form>
	</div>
</body>
</html>