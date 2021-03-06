<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />
<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<style>
.min-vh-100 {
	background-color: rgb(243, 243, 241)
}

.rod {
	border-radius: 30px;
}

.btn-primary1 {
	background-color: #7B7B7B;
	color: white;
}

.btn-success1 {
	background-color: #003366;
	color: white;
}

.btn-primary2 {
	background-color: #CC9966;
	color: white;
}

.btn-danger1 {
	background-color: #AD5A5A;
	color: white;
}

div.align-items-center {
	background-color: white
}

div#btnwrap {
	text-align: right;
}

body {
	background-color: rgb(243, 243, 241) font-family: "PingFang TC", 微軟正黑體,
		sans-serif;
	font-size: 16px;
	margin: 10px;
	padding: 0px;
	min-height: 100vh;
}

.row {
	margin: 15px;
}
</style>
<title>課程清單</title>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
				<c:choose>
					<c:when test="${not empty allCbList}">

						<div id="class-title" class="row">
							<div class="col-3">
								<h3>課程名稱</h3>
							</div>
							<div class="col-3">
								<h3>課程類型</h3>
							</div>

						</div>
						<hr>
						<c:forEach items="${allCbList}" var="cb">
							<div id="classDiv${cb.getCid()}"
								class="row align-items-center shadow rod">
								<div class="col-3">
									<p class="title m-0 m-auto">${cb.getTitle()}</p>
								</div>
								<div class="col-3">
									<p class="text m-0 m-auto">${cb.getClassType()}</p>
								</div>
								<div id="btnwrap" class="col justify-content-end pe-0">
									<a href="/SpecialTopic/class/update/${cb.getCid()}"
										class="btn btn-success1">編輯課程</a> <a
										href="/SpecialTopic/class/editCurriculum/${cb.getCid()}"
										class="btn btn-primary1">編輯章節</a> <a
										onclick="deleteClassByCid(${cb.getCid()})"
										class="btn btn-danger1">刪除課程</a>
									<c:choose>
										<c:when test="${null==cb.getClassOnlineBean()}">
											<a id="onlinebtn${cb.getCid()}"
												onclick="changeClassOnlineStatus(${cb.getCid()})"
												class="btn btn-primary2">上架課程</a>
										</c:when>
										<c:when test="${0==cb.getClassOnlineBean().getOnline()}">
											<a id="onlinebtn${cb.getCid()}"
												onclick="changeClassOnlineStatus(${cb.getCid()})"
												class="btn btn-primary2">上架課程</a>
										</c:when>
										<c:otherwise>
											<a id="onlinebtn${cb.getCid()}"
												onclick="changeClassOnlineStatus(${cb.getCid()})"
												class="btn btn-danger1">下架課程</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:forEach>
						<div class="row ">
							<div class="col m-auto">
								<a href="/SpecialTopic/create"
									class="btn btn-success align-items-center">新增課程</a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="home-tab"
									data-bs-toggle="tab" data-bs-target="#home" type="button"
									role="tab" aria-controls="home" aria-selected="true">未完成</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
									data-bs-target="#profile" type="button" role="tab"
									aria-controls="profile" aria-selected="false">已封存</button>
							</li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel"
								aria-labelledby="home-tab">
								<div>
									<c:choose>
										<c:when test="${not empty uncompleteList}">
											<div class="d-flex flex-wrap bd-highlight ">
												<c:forEach items="${uncompleteList}" var="cb">
													<div class="card m-3 shadow" style="width: 18rem;">
														<img class="card-img-top mh-25"
															src="https://iiiedug02.nilm.in${cb.getPhoto()}"
															alt="Card image cap">
														<div class="card-body">
															<h5 class="card-title">${cb.getTitle()}</h5>
															<p class="card-text">${cb.getClassType()}</p>
															<a href="/SpecialTopic/class/curriculums/${cb.getCid()}"
																class="btn btn-success">進入課程</a> <a
																onclick="changeStatusToOne(${cb.getCid()})"
																class="btn btn-danger1">移至已封存</a>
														</div>
													</div>
												</c:forEach>
											</div>
										</c:when>
										<c:otherwise>
								無未完成清單
								</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="tab-pane fade" id="profile" role="tabpanel"
								aria-labelledby="profile-tab">
								<div>
									<c:choose>
										<c:when test="${not empty completeList}">
											<div class="d-flex flex-wrap bd-highlight ">
												<c:forEach items="${completeList}" var="cb">
													<div class="card m-3" style="width: 18rem;">
														<img class="card-img-top"
															src="https://iiiedug02.nilm.in${cb.getPhoto()}"
															alt="Card image cap">
														<div class="card-body">
															<h5 class="card-title">${cb.getTitle()}</h5>
															<p class="card-text">${cb.getClassType()}</p>
															<a href="/SpecialTopic/classCurriculum/${cb.getCid()}"
																class="btn btn-success">進入課程</a> <a
																onclick="changeStatusToZero(${cb.getCid()})"
																class="btn btn-danger1">移至未完成</a>
														</div>
													</div>
												</c:forEach>
											</div>
										</c:when>
										<c:otherwise>
								還不趕快去上課！！
								</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

	<!-- ======= errMsg ======= -->
	<c:if test="${not empty msg}">
		<script>alert("${msg}")</script>
	</c:if>
</body>
</html>