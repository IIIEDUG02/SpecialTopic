<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  <!-- Head CSS -->
  <jsp:include page="../incloud/head-css.jsp" />
  
<title>curriculum</title>
</head>
<body>
	<div class="main-content--content--1Myl4">
		<div>
			<div class="curriculum-editor--alert-container--zgMwN">
				<div class="pb20">建立章節、講座和練習 (測驗、程式碼練習與作業)，開始組合您的課程。</div>
				<p data-purpose="free-course-message">如果您要免費提供課程，則課程的影片內容總長度必須少於
					2 小時。</p>
			</div>
			<div class="">
				<ul class="unstyled-list" data-purpose="curriculum-list">
					<li id="chapter0" class="curriculum-list--curriculum-list__section--2SsIO curriculum-list--curriculum-list__section--inline--2E-oq curriculum-list--add-item-always-visible--16dI6">
						<div class="curriculum-list--item-wrap--1GkZz curriculum-list--fake-section--1GsTK">
							<div class="curriculum-list--add-item-section-wrapper--bXWiB">
								<div class="curriculum-list--wrapper-section--3v7bx"></div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- ======= Footer ======= -->
  <jsp:include page="../incloud/footer-section.jsp" />

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Templete JS -->
  <jsp:include page="../incloud/body-js.jsp" />
  
</body>
</html>