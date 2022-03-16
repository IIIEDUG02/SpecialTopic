<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
<h3>Logout</h3>
<form action="/SpecialTopic/logout" method="post">
<table> <tr>
<td>Logout:</td>
<td><button type="submit" value="Logout">Logout</button></td> </tr>
</table>
<!-- <input type="hidden" id="${_csrf.parameterName}"> -->
    </form>
</body>
</html>