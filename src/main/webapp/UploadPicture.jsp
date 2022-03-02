<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="uploadToDB" method="POST" enctype="multipart/form-data">
		<input type="file" name="myfile">
		<button type="submit">上傳</button>
	</form>
</body>
</html>