<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新/修改章節資料</title>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/editCurriculum.js"></script>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />


<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}

video{
width: 854px;
height: 480px;
}
.btn-success{
margin-bottom: 50px;
}

</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div style="text-align:center">
			<button onclick='editCurriculum(0)' class="button btn-success">新增章節</button>
			</div>
			<div class="col shadow">
				<div id="formDiv"></div>
			</div>
			<div class="col shadow">
				<c:choose>
					<c:when test="${not empty cubList}">
						<ul class="list-group">
							<c:forEach items="${cubList}" var="cub">
								<li id="li${cub.getCuid()}" class="list-group-item"
									onclick="editCurriculum(${cub.getCuid()})">
									<div>
										<a>${cub.getChapter()}</a> <input id="cuid${cub.getCuid()}"
											type="hidden" value="${cub.getCuid()}" /> <input
											id="chapter${cub.getCuid()}" type="hidden"
											value="${cub.getChapter()}" /> <input
											id="videoPath${cub.getCuid()}" type="hidden"
											value="${cub.getVideo_path()}" />
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
					目前尚無章節,請點選新增章節
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
</body>
</html>