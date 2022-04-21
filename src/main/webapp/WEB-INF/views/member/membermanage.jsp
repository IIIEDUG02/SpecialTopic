<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- ======= Header ======= -->
<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />
<meta charset="UTF-8">
<title>admin</title>
<style>
.divtable1 {
	margin: 70px;
}

.search {
	margin: 20px 300px;
}
</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div style="height: 100px"></div>
		<div>
			<div class="search">
				<div class="input-group">
					<input id="search" type="text" class="form-control"
						placeholder="搜尋帳戶" name="username"
						aria-label="Recipient's username with two button addons">
					<input id="" class="btn btn-outline-secondary" onclick="mamermange()" type="button" value="搜尋">
					<input class="btn btn-outline-secondary" type="button" value="清除">
				</div>
			</div>
		</div>
	<div class="divtable1">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">編號</th>
					<th scope="col">帳號</th>
					<th scope="col">啟用</th>
				</tr>
			</thead>
			<tbody id="tbody1">
			</tbody>
		</table>
	</div>
</body>
</html>
