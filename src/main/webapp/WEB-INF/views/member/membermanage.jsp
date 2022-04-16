<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<!-- Templete JS -->
<jsp:include page="../incloud/body-js.jsp" />
<html>
<head>
<!-- ======= Header ======= -->
<jsp:include page="../member/manageHeader.jsp" />
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/mamermanage.js"></script>
<meta charset="UTF-8">
<title>admin</title>
<style>
a.a {
	color: #5FCF80;
}

span.bgfunction {
	color: #5FCF80;
}

li.dropdown {
	background-color: rgb(60, 60, 60);
}

ul.mydropdown {
	background-color: rgb(60, 60, 60);
}

.divtable1 {
	margin: 70px;
}

.search {
	margin: 20px 300px;
}

i.dropdown {
	color: #5FCF80;
}
</style>

</head>
<body style="background-color: rgb(210, 210, 210)">

	<div style="height: 100px"></div>
		<div>
			<div class="search">
				<div class="input-group">
					<input id="search" type="text" class="form-control"
						placeholder="搜尋帳戶" name="username"
						style="background-color: rgb(210, 210, 210); border-color: rgb(60, 60, 60)"
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
					<th scope="col">姓名</th>
					<th scope="col">啟用</th>
				</tr>
			</thead>
			<tbody id="tbody1">
				<tr >
					<th scope="row">1</th>
					<td>Mark</td>
					<td>on</td>
					<td><input type="submit" class="btn btn-success" value="編輯">
						<input type="button" style="margin-left: 30px"
						class="btn btn-secondary" value="重置"></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>on</td>
					<td><input type="submit" class="btn btn-success" value="編輯">
						<input type="button" style="margin-left: 30px"
						class="btn btn-secondary" value="重置"></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Larry the Bird</td>
					<td>off</td>
					<td><input type="submit" name="input1" class="btn btn-success"
						value="編輯"> <input type="button" style="margin-left: 30px"
						class="btn btn-secondary" value="重置"></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
