<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPercentInput</title>
<link href="/SpecialTopic/css/jquery-ui.min.css" rel="stylesheet" />
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="/SpecialTopic/js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#p1">JobPercentInput</a></li>
			<li><a href="#p2">GenderPercentInput</a></li>
			<li><a href="#p3">AgePercentInput</a></li>
		</ul>
		<div id="p1">
			<form action="getJobPercentbyID" method="get">
				<table>
					<tr>
						<td>InputID:</td>
						<td><input type="text" name="id" /></td>
						<td>${errors.jobid}</td>
					</tr>
					<tr>
						<td><button type="submit" value="login">送出</button></td>
						<td>${errors.jobmsg}</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="p2">
			<form action="getgenderbyID" method="get">
				<table>
					<tr>
						<td>InputID:</td>
						<td><input type="text" name="id" /></td>
						<td>${errors.genderid}</td>
					</tr>
					<tr>
						<td><button type="submit" value="login">送出</button></td>
						<td>${errors.gendermsg}</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="p3">
			<form action="getAgePercentbyID" method="get">
				<table>
					<tr>
						<td>InputID:</td>
						<td><input type="text" name="id" /></td>
						<td>${errors.ageid}</td>
					</tr>
					<tr>
						<td><button type="submit" value="login">送出</button></td>
						<td>${errors.agemsg}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>