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
	height: 340px;
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
		<form action="#" METHOD="GET">
			<div>
				全名:<input type="text" id="fullname" required="required"/>
				</td>
			</div>
			</br>
			<div>
				地址:<input type="text" id="address" required="required"/>
				</td>
			</div>
			</br>
			<div>
				電話:<input type="text" id="phone" required="required"/>
				</td>
			</div>
			</br>
			<div>
				生日:<input type="date" id="birthday" required="required"/>
				</td>
			</div>
			</br>
			<div>
				工作:<input type="text" id="job" required="required">
				</td>
			</div>
			</br>
			<div>
				身分證字號:<input type="text" id="identitycard" required="required">
				</td>
			</div>
			</br>
			<div>
				護照英文名子:<input type="text" id="passportname" required="required">
				</td>
			</div>
			</br>
			<div>
				<input type="button" onclick="register2()" value="送出" /> <input
					type="button"
					onclick="javascript:window.location = '/SpecialTopic/'"
					value="返回首頁" />
			</div>
		</form>
	</div>
	<c:if test="${not empty errMsg}">
			<script>alert("${errMsg}")</script>
		</c:if>
</body>
</html>