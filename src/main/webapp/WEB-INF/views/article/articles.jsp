<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 使用 security 標籤 library (必須先在 pom.xml 新增後才能使用) -->
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>OOXX線上教學平台</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- article CSS -->
<link rel="stylesheet" href="css/articles.css" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<style>
/* 自定義一些樣式 */
.create-article {
	display: flex;
	justify-content: center;
	margin-top: -20px;
	margin-bottom: 20px;
}

.create-article a {
	width: 200px;
	color: #fff;
	text-align: center;
}

.create-article a:hover {
	color: #fff;
}
</style>
</head>

<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />


	<!-- main start -->
	<div class="main">
		<!-- Articles block -->
		<div class="article-block">
      
      <!-- Category block -->
      <c:if test = "${not empty tag}">
        <div class="category-block">
          <div class="category-container mb">
            
            <!-- Breadcrumbs block -->
            <a href="articles">知識補給站</a>
            <span class="arrow-mb">&gt;</span>
            <a href="/articles?category=${tag.getCategory()}">${tag.getName()}</a>
          </div>
          
          <!-- Tag block -->
          <h1 class=tag-container>
            <span class="tag__text">${tag.getName()}</span>
          </h1>
        </div>
      </c:if>
    
			<!-- Articles container -->
			<div class="d-grid p-left">

				<!-- 判斷使用者是否已登入並且有 admin 角色才能夠新增文章 -->
        <c:if test = "${empty tag}">
  				<sec:authorize access="hasRole('ROLE_admin') and isAuthenticated()">
  					<div class="create-article">
  						<a href="articles/create" class="get-started-btn">發佈文章</a>
  					</div>
  				</sec:authorize>
        </c:if>

				<!-- Article -->
				<!-- 使用 for 迴圈取出每篇文章顯示在頁面上 -->
				<c:forEach var="article" items="${articles}">
					<div class="pb-and-bb">
						<!-- Article head -->
						<div class="d-flex">
							<!-- authorMeta -->
							<div id="authorMeta" class="author-meta">
								<div class="author-meta__author"></div>
								<!-- 取出發布文章的人，顯示 username -->
								${article.getMember().getUsername()}
							</div>

							<!-- publishDateMeta -->
							<div id="publishDateMeta" class="publish-date-meta">
								<img src="img/articles/calendar-icon.svg"
									class="publish-date-meta__img">
									<!-- 將文章發佈日期從 yyyy-mm-dd 轉成 yyyy/mm/dd -->
								<fmt:formatDate value="${article.getPublishTime()}"
									pattern="yyyy/MM/dd" />
							</div>

							<!-- viewCountMeta -->
							<div id="viewCountMeta" class="view-count-meta">
								<!-- 顯示文章瀏覽次數 -->
								<img src="img/articles/eye-icon.svg" alt="" class="view-count-meta__img"> ${article.getPageViews()}
							</div>
						</div>

						<!-- Article body -->
						<a href="/SpecialTopic/articles?article=${article.getUuid()}"
							class="article-body"> 
							<!-- Article title -->
							<!-- 顯示文章標題 -->
							<h2 title="${article.getTitle()}" class="article-body__title">${article.getTitle()}
							</h2> <!-- Article img -->
							<img ${thumbnails.get(article.getId())}
							class="article-body__img"> 
							<!-- Article content -->
							<!-- 顯示文章內容 -->
							<div class="article-body__content">${abbreviations.get(article.getId())}
							</div>
						</a>

						<!-- Article tags -->
						<div class="article-tags">
							<!-- for 迴圈取出每篇文章的所有標籤 -->
							<c:forEach var="tag" items="${article.getTags()}">
								<a href="articles?category=${tag.getCategory()}" class="article-tags__tag article-tags--margin">
									<!-- 顯示文章標籤 -->
									<span class="article-tags__symbol">#</span> ${tag.getName()}
								</a>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- All categories block -->
		<div class="all-categories-block">
			<!-- All categories container -->
			<div class="all-categories__container all-caregories--position">
				<!-- All categories head -->
				<h2 class="all-categories__title">所有分類</h2>

				<!-- All categories body -->
				<div class="all-categories-body">
					<!-- All categories content -->
					<!-- 只顯示所有類別的標籤 -->
					<c:forEach var="tag" items="${tags}">
						<a href="articles?category=${tag.getCategory()}"
							class="all-caregories-body__category all-categories-body--margin">
							<!-- 顯示標籤中文名稱 -->
							<span class="article-tags__symbol">#</span> ${tag.getName()}
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- main end -->

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />
  
  <!-- Toast -->
  <jsp:include page="toast.jsp" />
  
	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
  
  <!-- Toast js -->
  <script src="/SpecialTopic/js/toast.js"></script>
  
  <script>
  	$(document).ready(() => {
  		if (window.location.href.endsWith('?create=success'))
  	  		showToast('您的文章已發佈成功！')
  	})
  </script>
</body>
</html>