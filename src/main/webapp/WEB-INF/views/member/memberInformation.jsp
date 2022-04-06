<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<title>Insert title here</title>
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
 		<form action="/SpecialTopic/memberUpdateInformation" method="post">
 			<div>帳號: <a>${mb.getUsername()}</a><input type="hidden" name="username" value="${mb.getUsername()}"></div>
 			<div>密碼: <a>************</a> <input type="hidden" name="password" value="${mb.getPassword()}"> <button >編輯</button></div>
 			<div>姓名: <a>${mb.getMemberInformation().getFullname()}</a> <input type="hidden" name="fullname" value="${mb.getMemberInformation().getFullname()}"> <button >編輯</button></div>
 			<div>手機: <a>${mb.getMemberInformation().getPhone()}</a> <input type="hidden" name="phone" value="${mb.getMemberInformation().getPhone()}" > <button >編輯</button></div>
 			<div>信箱: <a>${mb.getMemberInformation().getEmail()}</a> <input type="hidden" name="email" value="${mb.getMemberInformation().getEmail()}" > <button >編輯</button></div>
 			<div>地址: <a>${mb.getMemberInformation().getAddress()}</a> <input type="hidden" name="adress" value="${mb.getMemberInformation().getAddress()}" > <button >編輯</button></div>
 			<div>工作: <a>${mb.getMemberInformation().getJob()}</a> <input type="hidden" name="job" value="${mb.getMemberInformation().getJob()}" > <button >編輯</button></div>
 			<input id="check" type="hidden" value="確認" >
 		</form>
 	</div>
</body>
<script>
function editMemberInformation(){
	  var pwdInput = $(this).parent().children("input");
	  if(pwdInput.attr("name") =='password'){
		  	pwdInput.val('');
		  }
	  $(this).parent().children("a").html("");
	  $(this).parent().children("input").attr("type","text");
	  $(this).remove();
	  $('input#check').attr("type","submit");
}

$('button').click(editMemberInformation);
</script>
</html>