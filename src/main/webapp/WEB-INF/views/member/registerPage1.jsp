<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	height: 200px;
	padding: 50px;
	box-shadow: 5px 5px 10px #999;
	border: 1px solid #fff text-align:center;
	font-size: 1.0em;
}
</style>
</head>
    <script>
		function addCheck(){
			var username=document.getElementById("username").value;
			var password=document.getElementById("password").value;
			var repassword=document.getElementById("repassword").value;
			var email=document.getElementById("email").value;
			if(username==""){
				alert("使用者名稱不能為空!");
				document.getElementById("username").focus();  
				return false;
                }
			if(password==""){
				alert("密碼不能為空!");
				 document.getElementById("password").focus();
				 return false;
				 }
			if(password != repassord){
				alert("兩次輸入密碼不相同!");
				 document.getElementById("repassword").focus();
				 return false;
				 }
		}
		function validate(){
		    var flag = addCheck();
		    if(flag == false)
		        return false;
		    return true;
	    }
	</script>
<body>
	<div class="main-box">
		<h3 style="text-align: center">帳號註冊</h3>
		<form action="#" METHOD="GET" onsubmit="return validate()">
		<table>

			<div>
				帳號:<input type="text" id="username" name="username"
					required="required" value="輸入16個字元以內" maxlength = "16" onfocus = "if(this.value == '輸入16個字元以內') this.value =''"/>
			</div>
			</br>
			<div>
				密碼:<input type="text" id="password" name="password" required="required"
				 value="輸入20個字元以內" maxlength = "20" onfocus = "if(this.value == '輸入20個字元以內') this.value =''"/> 
			</div>
			</br>
			<div>
				確認密碼:<input type="text" id="repassword" name="repassword" required="required" 
				value="重新輸入密碼" maxlength = "20" onfocus = "if(this.value == '重新輸入密碼') this.value =''"/>
			</div>
			</br>
			<div>
				<label>電子信箱:</label><input type="text" id="email" name="email" required="required"/>
			</div>
			</br>
			<div>
				<input type="button" onclick="register1()" value="送 出" />
				
				<input
					type="button"
					onclick="javascript:window.location = '/SpecialTopic/'"
					value="返 回 首 頁" />				
			</div>
		</table>	
		</form>
	</div>
</body>
</html>