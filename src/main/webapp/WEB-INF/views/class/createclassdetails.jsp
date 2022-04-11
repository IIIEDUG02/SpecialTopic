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
	<form action="createclassdetails" METHOD="POST" >
		<table>
			<tr>
				<td><label>描述:</label><input type="text" id="descript"
					name="descript" /></td>
			</tr>
			<tr>
				<td><label>自備工具:</label><input type="text" id="needed_tool"
					name="needed_tool" /></td>
			</tr>
			<tr>
				<td><label>學生要求:</label><input type="text" id="stu_required"
					name="stu_required" /></td>
			</tr>
			<tr>
				<td><label>影片長度:</label><input type="text" id="length_min"
					name="length_min" /></td>
			</tr>
			<tr>
				<td><label>目標:</label><input type="text" id="goal"
					name="goal" /></td>
			</tr>
			<tr>
				<td><label>影片連結:</label><input type="text" id="video"
					name="video" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="下一步" /> <input type="button"
					onclick="javascript:window.location = '/'" value="返回首頁" /></td>
			</tr>
		</table>
	</form>
</body>
</html>