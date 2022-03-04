<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${ empty username }">
		<form action="login" method="POST">
			帳號:<input type="text" name="username" placeholder="${ err.username }"> 
			密碼:<input type="password" name="password" placeholder="${ err.password }">
			<button type="submit">登入</button> <a style="color: red">${ result }</a>
		</form>
	</c:when>
	<c:otherwise>  
		<form action="logout">HI,${ username }<button type="submit">登出</button></form>
	</c:otherwise>
</c:choose>
