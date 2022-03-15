<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>teacherpage</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<table>
		<thead>
			<tr>
				<td>課程名稱:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tc" items="${teacherclass}">
				<tr>
					<td><c:out value="${tc.getTitle()}" /></td>
					<td><c:out value=" " /></td>
					<td><c:out value="${tc.getClassType()}" /></td>
					<td><c:out value="  " /></td>
					<input type="button" onclick="javascript:window.location = '/SpecialTopic/deleteclass/${tc.getCid()}'" value="刪除課程"/>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" onclick=""javascript:window.location = '/'" value="返回首頁"/>
	<!-- 頁尾 -->
	<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>