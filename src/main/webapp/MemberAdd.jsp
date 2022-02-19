<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include   page="/WEB-INF/view/header.jsp" flush="true"/>
<form action="SignUp" method="post">
帳號：	<input id="" name="username" 	type="text" value="nilm987521"><br>
密碼：	<input id="" name="password" 	type="password" value="abcd1234"><br>
密碼確認：<input id="" name="password2" 	type="password" value="abcd1234"><br>
姓名：	<input id="" name="fullname" 	type="text" value="藍翊家"><br>
E-mail：	<input id="" name="email" 		type="text" value="nilm987521@gmail.com"><br>
生日：	<input id="" name="birthday" 	type="date" value="1991-08-30"><br>
地址：	<input id="" name="address" 	type="text" value="台灣好棒棒"><br>
電話：	<input id="" name="phone" 		type="text" value="0987294379"><br>
職業：	<input id="" name="job" 		type="text" value="程序猿、攻城獅"><br>
<button type="submit">註冊</button><button onclick="javascript:history.back()" >放棄</button>
</form>
</body>
</html>