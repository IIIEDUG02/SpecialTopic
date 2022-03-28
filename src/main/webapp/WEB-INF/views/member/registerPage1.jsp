<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js" ></script>
<script type="text/javascript" src="/SpecialTopic/js/registerjs.js" ></script>
<title>帳號註冊</title>
</head>
<body>
	<h3>帳號註冊</h3>
		<form action="#" METHOD="GET">
		<table>
			<tr>
				<td><label>帳號:</label><input type="text" id="username" /></td>
			</tr>
			<tr>
				<td><label>密碼:</label><input type="text" id="password" /></td>
			</tr>
			<tr>
				<td><label>電子信箱:</label><input type="text" id="email" /></td>
			</tr>
			<tr>
				<td>
					<input type="button" onclick="register1()" value="送出" />
					<input type="button" onclick="javascript:window.location = '/SpecialTopic/'" value="返回首頁"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>