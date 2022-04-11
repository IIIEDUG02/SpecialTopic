
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

/* 編輯文章 button 訪問過後樣式 */
.btn-outline-success:visited {
  color: #198754;
  border-color: #198754;
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
  				<sec:authorize access="isAuthenticated() and (hasRole('ROLE_admin') or hasRole('ROLE_teacher'))">
  					<div class="create-article">
  						<a href="articles/create" class="get-started-btn">發佈文章</a>
              <a class="get-started-btn" data-bs-toggle="offcanvas" 
                 data-bs-target="#articleOffcanvas" 
                 aria-controls="articleOffcanvas">文章操作</a>
  					</div>
  				</sec:authorize>
        </c:if>

				<!-- Article -->
				<!-- 使用 for 迴圈取出每篇文章顯示在頁面上 -->
				<c:forEach var="article" items="${articles}">
					<div id="${article.getUuid()}" class="pb-and-bb">
						<!-- Article head -->
						<div class="d-flex">
							<!-- authorMeta -->
							<div id="authorMeta" class="author-meta">
								<div class="author-meta__author"></div>
								<!-- 取出發布文章的人，顯示 username -->
								${article.getMember().getMemberInformation().getFullname()}
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
  
  <!-- Left offcanvas -->
  <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="articleOffcanvas" aria-labelledby="articleOffcanvasLabel" style="width: 600px !important;">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="articleOffcanvasLabel">文章操作介面</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  
  <!-- Offcanvas body -->
  <div class="offcanvas-body">
    <!-- Titles container -->
    <ul id="articlesContainer" class="list-group list-group-flush">
      <c:forEach var="article" items="${articles}">
        <li class="list-group-item d-flex align-items-center" data-id="${article.getUuid()}" style="justify-content: flex-start;">
        <!-- Delete button -->
        <button type="button" class="articleDelBtn btn btn-outline-danger btn-sm" title="刪除" style="margin-right: 5px;">
          <i class="bi bi-trash-fill"></i>
        
        <!-- Update button -->
        </button>
        <a
          href="articles/update/${article.getUuid()}"
          type="button" class="articleUpdateBtn btn btn-outline-success btn-sm" title="編輯" 
          style="margin-right: 10px;">
            <i class="bi bi-file-earmark-font-fill"></i>
        </a>
          <!-- Title text -->
          ${article.getTitle()}
      </c:forEach>
    </ul>
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
  
  <!-- Base js -->
  <script src="/SpecialTopic/js/base.js"></script>
  <script>
  	
  	/* 
  	 * 因刪除文章列表上要實做的功能有些邏輯較複雜，因此寫 class 來輔助
  	 */
  	class ArticlesPage extends Base {
  	  // 宣告靜態變數
  	  static HTTP_OK = "200"
  	  static TIMEOUT_SEC = 5
  	  static DELETE_URL = 'articles/delete'
  	  static CREATE_SUCCESS_URL = '?create=success'
	    static UPDATE_SUCCESS_URL = '?update=success'
  	  static CREATE_MSG = '您的文章已發佈成功！'
	    static UPDATE_MSG = '您的文章已更新成功！'
  	  static DELETE_MSG = '您已經成功刪除文章！'
  	  
  	  // 建構子
  	  constructor() {
  	    super()
  	    this.body = document.querySelector('body')
  	    
  	    // alert window template
  	    this.alert = `
  	      <div id="alertMask" class="modal-backdrop fade show" style="z-index: 9999!important; opacity: .5!important;"></div>
  	      <div id="alertToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true"
  	        	 style="display: block!important; z-index: 9999; position: fixed; left: 50%; top: 50%; transform: translate(-50%, -50%);"
        	>
    	      <div class="toast-body">
    	      	<span id="alertToastBody"></span>
    	        <div class="mt-2 pt-2 border-top">
    	          <button id="alertDelBtn" type="button" class="btn btn-primary btn-sm">確定</button>
    	          <button id="alertCancelBtn" type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="toast">取消</button>
    	        </div>
    	      </div>
  	    	</div>
  	    `
  	    
  	    this.articlesContainer = document.querySelector('#articlesContainer')
  	    this.articles = [...this.articlesContainer.querySelectorAll('li')]
  	    this.articleDelBtns = [...this.articlesContainer.querySelectorAll('.articleDelBtn')]
  	    
  	    this.init()
  	  }
  	  
  	  init() {
  	    // 先使用較簡易暴力的方式來實現發佈文章後的提示訊息
  	    if (window.location.href.endsWith(ArticlesPage.CREATE_SUCCESS_URL))
  	  		this.showToast(ArticlesPage.CREATE_MSG)
  	  	// 更新文章成功訊息
  	  	if (window.location.href.endsWith(ArticlesPage.UPDATE_SUCCESS_URL))
  	  		this.showToast(ArticlesPage.UPDATE_MSG)
  	  	
  	  	// 為所有的刪除文章 button 新增 click event
  			for (let btn of this.articleDelBtns)
	  		  btn.addEventListener('click', this.showAlert.bind(null, event, btn, this))
  	  }
			
  		// 刪除文章的事件處理器
  	  async deleteArticleHandler(e, btn, obj) {
  		  // 取得文章的 UUID
  		  const li = btn.parentElement
  	    const uuid = li.dataset.id
  	    const article = document.getElementById(uuid)
  	    
  	    // 顯示 Loading... 畫面
  	    obj.showSpinner()
  	    
  	    try {
  	      // 向後端發送 AJAX 請求並等待結果回傳,await=等待
  	      const result = await ArticlesPage.ajax("POST", ArticlesPage.DELETE_URL, {uuid: uuid})
  	     	
  	      if (result.response) {
  	    		console.log(result)
  	        li.remove()
  	        article.remove()
  	        obj.showToast(ArticlesPage.DELETE_MSG)
  	      } else {
  	        console.log(result)
  	      }
  	    } catch (error) {
  	      console.error(error)
	      } finally {
	        obj.removeSpinner()
	      }
  	  }
  		
  		// 移除題醒元件
  		removeAlert() {
  		  const mask = document.querySelector('#alertMask')
			  const alert = document.querySelector('#alertToast')
			  
			  this.body.removeChild(mask)
  		  this.body.removeChild(alert)
  		}
  		
  		// 刪除 button 的事件處理器，會先提醒是否要刪除該文章
			showAlert(e, btn, obj) {
  		  // 添加提醒視窗至 body
			  obj.body.insertAdjacentHTML('beforeend', obj.alert)
			  
			  const li = btn.parentElement
			  const toastBody = document.querySelector('#alertToastBody')
			  const alertDelBtn = document.querySelector('#alertDelBtn')
			  const alertCancelBtn = document.querySelector('#alertCancelBtn')
			  
			  // 設定提醒文字
			  toastBody.innerText = '您確定要刪除文章 ' + '"' +li.innerText + '" 嗎？'
			  
			  // "確定" button 的事件
			  alertDelBtn.addEventListener('click', () => {
			    obj.removeAlert()
			    obj.deleteArticleHandler(null, btn, obj)
			  })
			  
			  // "取消" button 的事件
			  alertCancelBtn.addEventListener('click', obj.removeAlert.bind(obj))
			}
  	}
  	
  	// Entry point
  	$(document).ready(() => {  	  
  		new ArticlesPage()
  	})
  </script>
</body>
</html>