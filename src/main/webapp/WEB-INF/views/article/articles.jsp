<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 使用 security 標籤 library (必須先在 pom.xml 新增後才能使用) -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

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
<link rel="stylesheet" href="/SpecialTopic/css/articles.css" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<!-- 新增了一些額外樣式 -->
<style>
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
			<!-- Articles container -->
			<div class="d-grid p-left">
			
				<!-- 判斷使用者是否已登入並且有 admin 角色才能夠新增文章 -->
				<!-- hasRole('ROLE_admin'): 判斷是否擁有某個角色(在這裡是判斷是否有管理員角色) -->
				<!-- isAuthenticated(): 判斷是否已經登入 -->
		        <sec:authorize access="hasRole('ROLE_admin') and isAuthenticated()">
		          <div class="create-article">
		            <a href="articles/create" class="get-started-btn">發佈文章</a>
		          </div>
		        </sec:authorize>
        
				<!-- Article -->
				<div class="pb-and-bb">
					<!-- Article head -->
					<div class="d-flex">
						<!-- authorMeta -->
						<div id="authorMeta" class="author-meta">
							<div class="author-meta__author"></div>
							福利社社長
						</div>

						<!-- publishDateMeta -->
						<div id="publishDateMeta" class="publish-date-meta">
							<img src="img/articles/calendar-icon.svg"
								class="publish-date-meta__img"> 2022/03/08
						</div>

						<!-- viewCountMeta -->
						<div id="viewCountMeta" class="view-count-meta">
							<img src="img/articles/eye-icon.svg" alt=""
								class="view-count-meta__img"> 1639
						</div>
					</div>

					<!-- Article body -->
					<a href="#" class="article-body"> 
						<!-- Article title -->
						<h2 title="【專題】接案維生前，請詳閱說明書——給文字接案工作者的生存攻略"
							class="article-body__title">【專題】接案維生前，請詳閱說明書——給文字接案工作者的生存攻略
						</h2> <!-- Article img --> <img src="img/articles/article-img1.avif"
						class="article-body__img"> <!-- Article content -->
						<div class="article-body__content">Hahow
							為你策劃〈文字接案工作者生存攻略〉系列專題，與你分享寫作與職涯發展的關係、文字工作者的接案心法以及提升文字技能的方法。在踏上文字接案之路前，先來裝備。
						</div>
					</a>

					<!-- Article tags -->
					<div class="article-tags">
						<a href="#" class="article-tags__tag article-tags--margin"> <span
							class="article-tags__symbol">#</span> 職場必備技能
						</a> <a href="#" class="article-tags__tag article-tags--margin"> <span
							class="article-tags__symbol">#</span> 找工作
						</a>
					</div>
				</div>

				<!-- Article -->
				<div class="sc-p3nuo7-0 pb-and-bb">
					<!-- Article head -->
					<div class="d-flex">
						<!-- authorMeta -->
						<div id="authorMeta" class="author-meta">
							<div class="author-meta__author"></div>
							福利社社長
						</div>

						<!-- publishDateMeta -->
						<div id="publishDateMeta" class="publish-date-meta">
							<img src="img/articles/calendar-icon.svg"
								class="publish-date-meta__img"> 2022/03/09
						</div>

						<!-- viewCountMeta -->
						<div id="viewCountMeta" class="view-count-meta">
							<img src="img/articles/eye-icon.svg" alt=""
								class="view-count-meta__img"> 1347
						</div>
					</div>

					<!-- Article body -->
					<a href="#" class="article-body"> 
						<!-- Article title -->
						<h2 title="【專題】關於資料分析這件事——趁著大數據浪潮，探索數據分析的多種可能"
							class="article-body__title">
							【專題】關於資料分析這件事——趁著大數據浪潮，探索數據分析的多種可能</h2> 
							<!-- Article img --> 
							<img src="img/articles/article-img2.avif" class="article-body__img">

						<!-- Article content -->
						<div class="article-body__content">Hahow
							為你策劃〈關於資料分析這件事〉系列專題，邀請業界數據分析師分享數據分析職涯的經驗談，希望過來人的探索經驗可以成為你的參考指南，伴你探索資料科學的商業價值與職涯可能性。
						</div>
					</a>

					<!-- Article tags -->
					<div class="article-tags">
						<a href="#" class="article-tags__tag article-tags--margin"> <span
							class="article-tags__symbol">#</span> 職場必備技能
						</a> <a href="#" class="article-tags__tag article-tags--margin"> <span
							class="article-tags__symbol">#</span> 找工作
						</a>
					</div>
				</div>
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
					<a href="#"
						class="all-caregories-body__category all-categories-body--margin">
						<span class="article-tags__symbol">#</span> 理想 生活
					</a> <a href="#"
						class="all-caregories-body__category all-categories-body--margin">
						<span class="article-tags__symbol">#</span> 職場必備技能
					</a> <a href="#"
						class="all-caregories-body__category all-categories-body--margin">
						<span class="article-tags__symbol">#</span> 學語言
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- main end -->

	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />

	<!-- ======= errMsg ======= -->
	<c:if test="${not empty errMsg}">
		<script>
			alert("${errMsg}")
		</script>
	</c:if>

</body>

</html>