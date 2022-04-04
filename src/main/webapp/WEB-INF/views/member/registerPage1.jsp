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
		<form action="/SpecialTopic/registerAction1" METHOD="POST"
			onsubmit="return validate()">
			<div>
				帳號:<input type="text" id="username" name="username"
					required="required" maxlength="16"
					onfocus="if(this.value == '輸入16個字元以內') this.value =''" />
			</div>
			<br/>
			<div>
				密碼:<input type="password" id="password" name="password"
					required="required" maxlength="20"
					onfocus="if(this.value == '輸入20個字元以內') this.value =''" />
			</div>
			<br/>
			<div>
				確認密碼:<input type="password" id="repassword" name="repassword"
					required="required" maxlength="20"
					onfocus="if(this.value == '重新輸入密碼') this.value =''" />
			</div>
			<br/>
			<div>
				全名:<input type="text" id="fullname" required="required"/>			
			</div>
			<br/>
			<div>
				電子信箱:<input type="text" id="email" name="email" required="required" />
			</div>
			<br/>
			<div>
				地址:<input type="text" id="address" required="required"/>				
			</div>
			<br/>
			<div>
				電話:<input type="text" id="phone" required="required"/>				
			</div>
			<br/>
			<div>
				生日:<input type="date" id="birthday" required="required"/>				
			</div>
			<br/>
				<div>
				工作:<input type="text" id="job" required="required">				
			</div>
			<br/>
			<div>
				身分證字號:<input type="text" id="identitycard" required="required">
			</div>
			<br/>
			<div>
				護照英文名子:<input type="text" id="passportname" required="required">				
			</div>
			<br/>
			<div>
				性別: 男<input type="checkbox" id="gender" />女<input type="checkbox" id="gender" />				
			</div>
			<br/>
			<div>
				<input type="submit" value="送 出" /> <input type="button"
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