<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
</head>
<body>
	<form method="post" action="login">
		帳號： <input id="username" name="username" type="text" value=""><a>${err.username}</a><br />
		密碼： <input id="password" name="password" type="password" value=""><a>${err.password}</a><br />
		<input type="submit" value="登入" /><a>${err.result}</a>
	</form>
</body>
</html>