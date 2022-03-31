<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>課程創建</h3>
		<form action="#" METHOD="GET">
		<table>
			<tr>
				<td><label>標題:</label><input type="text" id="classtitle" /></td>
			</tr>
			<tr>
				<td><label>類型:</label><input type="text" id="classtype" /></td>
			</tr>
			<tr>
				<td><label>價錢:</label><input type="text" id="classprice" /></td>
			</tr>
			<tr>
				<td><label>縮圖:</label><input type="file" id="classphoto" /></td>
			</tr>
			<tr>
				<td><label>老師:</label><input type="text" id="teacherid" /></td>
			</tr>
			<tr>
				<td>
					<input type="button" onclick="register1()" value="送出" />
					<input type="button" onclick="javascript:window.location = '/'" value="返回首頁"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>