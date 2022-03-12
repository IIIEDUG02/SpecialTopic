<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${ empty username }">
	<!-- action 一定要是自己 -->
		<form action="/login_page" method="POST">
			帳號:<input type="text" name="username" placeholder="${ err.username }"> 
			密碼:<input type="password" name="password" placeholder="${ err.password }">
			<button type="submit">登入</button> <a style="color: red">${ result }</a>
			<input type="checkbox" name="rememberMe-key" />RememberMe
		</form>
	</c:when>
	<c:otherwise>  
		<form action="logout">HI,${ username }<button type="submit">登出</button></form>
	</c:otherwise>
</c:choose>
