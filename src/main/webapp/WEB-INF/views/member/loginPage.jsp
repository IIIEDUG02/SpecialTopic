<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<script>
	function loginVerify() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if (username == '') {
			alert('帳號不能為空白!');
			return;
		}
		if (password == '') {
			alert('密碼不能為空白!');
			return;
		}
	}
</script>
<body>
	<form action="/SpecialTopic/login_page" method="POST">
		帳號:<input type="text" id="username" name="username"
			placeholder="${ err.username }" /> </br> 密碼:<input type="password"
			id="password" name="password" placeholder="${ err.password }">
		<button type="submit" name="loginButton" onclick="loginVerify();">登入</button>
		<a style="color: red">${ result }</a> <input type="checkbox"
			name="rememberMe-key" />記住我
	</form>
	<%-- <form action="logout">HI,${ username }<button type="submit">登出</button></form> --%>
</body>
</html>
