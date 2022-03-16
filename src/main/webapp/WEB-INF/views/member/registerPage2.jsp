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
				<td><label>全名:</label><input type="text" id="fullname" /></td>
			</tr>
			<tr>
				<td><label>地址:</label><input type="text" id="address"/></td>
			</tr>
			<tr>
				<td><label>電話:</label><input type="text" id="phone" /></td>
			</tr>
			<tr>
				<td><label>生日:</label><input type="date" id="birthday" /></td>
			</tr>
			<tr>
				<td><label>工作:</label><input type="text" id="job"></td>
			</tr>
			<tr>
				<td>
					<input type="button" onclick="register2()" value="送出" />
					<input type="button" onclick="javascript:window.location = '/'" value="返回首頁"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>