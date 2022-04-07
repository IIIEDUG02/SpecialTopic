<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/registerjs.js"></script>
<title>帳號註冊</title>
<style>
.main-box {
	margin: 50px auto;
	width: 400px;
	height: 540px;
	padding: 50px;
	box-shadow: 5px 5px 10px #999;
	border: 1px solid #fff text-align:center;
	font-size: 1.0em;
}
</style>
</head>
<body>
	<div class="main-box">
		<h3 style="text-align: center">帳號註冊</h3>
		<form action="/SpecialTopic/registerAction1" id="registerform" METHOD="POST" >
			<div>
				帳號:<input type="text" id="username" name="username"
					required="required" maxlength="16" />
			</div>
			<br/>
			<div>
				密碼:<input type="password" id="password" name="password"
					required="required" maxlength="20" />
			</div>
			<br/>
			<div>
				確認密碼:<input type="password" id="repassword" name="repassword"
					required="required" maxlength="20" />
			</div>
			<br/>
			<div>
				全名:<input type="text" name="fullname" required="required"/>			
			</div>
			<br/>
			<div>
				電子信箱:<input type="text" name="email" id="email" required="required" />
			</div>
			<br/>
			<div>
				地址:<input type="text" name="address" required="required"/>				
			</div>
			<br/>
			<div>
				手機:<input type="text" name="phone" id="phone" required="required"/>				
			</div>
			<br/>
			<div>
				生日:<input type="date" name="birthday" required="required"/>				
			</div>
			<br/>
				<div>
				工作:<input type="text" name="job" required="required">				
			</div>
			<br/>
			<div>
				身分證字號:<input type="text" id="identitycard" name="identitycard" required="required">
			</div>
			<br/>
			<div>
				護照英文名子:<input type="text" name="passportname" required="required">				
			</div>
			<br/>
			<div>
				性別: 男<input type="radio" name="gender" value=1 />女<input type="radio" name="gender" value=0 />				
			</div>
			<br/>
			<div>
				<input type="button" value="送 出" onclick="check()"/> <input type="button"
					onclick="javascript:window.location = '/SpecialTopic/'"
					value="返 回 首 頁" />
			</div>
		</form>
		<c:if test="${not empty errMsg}">
			<script>alert("${errMsg}")</script>
		</c:if>
	</div>
</body>
</html>