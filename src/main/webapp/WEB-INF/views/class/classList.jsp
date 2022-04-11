<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/class.js"></script>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<title>student</title>
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
						<c:forEach items="${allCbList}" var="cb">
							<div id="classDiv${cb.getCid()}" class="card m-3" style="width: 18rem;">
								<img class="card-img-top" src="${cb.getPhoto()}"
									alt="Card image cap">
								<div class="card-body">
									<h5 class="card-title">${cb.getTitle()}</h5>
									<p class="card-text">${cb.getClassType()}</p>
									<a href="/SpecialTopic/class/update/${cb.getCid()}" class="btn btn-primary">編輯課程</a> 
									<a onclick="deleteClassByCid(${cb.getCid()})" class="btn btn-primary">刪除課程</a> 
									<a href="/SpecialTopic/class/editCurriculum/${cb.getCid()}" class="btn btn-primary">編輯章節</a>
								</div>
							</div>
						</c:forEach>
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
											<div class="d-flex flex-row bd-highlight mb-3">
												<c:forEach items="${uncompleteList}" var="cb">
													<div class="card m-3" style="width: 18rem;">
														<img class="card-img-top" src="${cb.getPhoto()}"
															alt="Card image cap">
														<div class="card-body">
															<h5 class="card-title">${cb.getTitle()}</h5>
															<p class="card-text">${cb.getClassType()}</p>
															<a href="/SpecialTopic/class/curriculums/${cb.getCid()}"
																class="btn btn-primary">進入課程</a>
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
											<div class="d-flex flex-row bd-highlight mb-3">
												<c:forEach items="${completeList}" var="cb">
													<div class="card m-3" style="width: 18rem;">
														<img class="card-img-top" src="${cb.getPhoto()}"
															alt="Card image cap">
														<div class="card-body">
															<h5 class="card-title">${cb.getTitle()}</h5>
															<p class="card-text">${cb.getClassType()}</p>
															<a href="/SpecialTopic/classCurriculum/${cb.getCid()}"
																class="btn btn-primary">進入課程</a>
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