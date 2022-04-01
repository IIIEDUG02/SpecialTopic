
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

/* 調整 spinner style */
.spinner-border {
  position: fixed;
  left: 45%;
  top: 45%;
  width: 5rem;
  height: 5rem;
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
        <button type="button" class="articleUpdateBtn btn btn-outline-success btn-sm" title="更新" style="margin-right: 10px;">
          <i class="bi bi-arrow-clockwise"></i>
        </button>
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
  
  <!-- Toast js -->
  <script src="/SpecialTopic/js/toast.js"></script>
  
  <script>
  	/* 
  	 * 因刪除文章列表上要實做的功能有些邏輯較複雜，因此寫 class 來輔助
  	 */
  	class ArticlesPage {
  	  // 宣告靜態變數
  	  static HTTP_OK = "200"
  	  static TIMEOUT_SEC = 5
  	  static DELETE_URL = 'articles/delete'
  	  static CREATE_SUCCESS_URL = '?create=success'
  	  static CREATE_MSG = '您的文章已發佈成功！'
  	  static DELETE_MSG = '您已經成功刪除文章！'
  	  
  	  // 建構子
  	  constructor() {
  	    this.body = document.querySelector('body')
  	    
  	    // Loading... 的 html template
  	    this.template = `
  	    	<div class="modal-backdrop fade show"></div>
  	    	<div class="spinner-border text-secondary" role="status">
  	    		<span class="visually-hidden">Loading...</span>
	    		</div>`
  	    this.articlesContainer = document.querySelector('#articlesContainer')
  	    this.articles = [...this.articlesContainer.querySelectorAll('li')]
  	    this.articleDelBtns = [...this.articlesContainer.querySelectorAll('.articleDelBtn')]
  	    this.show = showToast
  	    
  	    this.init()
  	  }
  	  
  	  init() {
  	    // 先使用較簡易暴力的方式來實現發佈文章後的提示訊息
  	    if (window.location.href.endsWith(ArticlesPage.CREATE_SUCCESS_URL))
  	  		this.show(ArticlesPage.CREATE_MSG)
  	  	
  	  	// 為所有的刪除文章 button 新增 click event
  			for (let btn of this.articleDelBtns)
	  		  btn.addEventListener('click', this.deleteArticleHandler.bind(null, event, btn, this))
  	  }
  	  
  		// 請求超時中斷函數
  	  static timeout(s) {
  	    return new Promise(function (_, reject) {
  	      setTimeout(function () {
  	        reject(new Error(`請求時間過長(${s} 秒)。`))
  	      }, s * 1000)
  	    })
  	  }
  		
		// 使用原生(native)的方式(JS)來發 AJAX 請求,async JS 的非同步關鍵字
		// undefined JS 的關鍵字(未定義 有功能的字)
		// 跟 JAVA 一樣，函數可以傳參數進來，formData 是第一個參數，csrf 是第二個參數
		// csrf = Cross Site Request Forgery 跨站請求偽造(防止請求偽造)
  	  static async ajax(formData = undefined, csrf = undefined) {
  	    try {
  	      // const 表示是常數，ex; const a = 1，之後改成 a = 2 會錯誤，因為常數只能賦值(給予變數的值)一次
  	      // fetch 是一個 JS 的函數，能夠用來發送 AJAX 請求
  	      const fetchPro = fetch(ArticlesPage.DELETE_URL, {
  	        method: 'POST',
  	        headers: {
  	          'Content-Type': 'application/json',
  	          // 專案目前取消 CSRF，因此先註解
  	          //之後上伺服器後，下面那句要解開，向後端傳送一個確認是否偽造請求的驗證碼
  	          // 'X-CSRF-TOKEN': csrf,  
  	        },
  	        // body 用來放要往後端傳送的資料
  	        // JSON 是 JS 的物件，stringify 用來把傳入的參數轉成 JSON 格式
  	        body: JSON.stringify(formData),
  	      })
  	      
  	      // 等待後端回傳結果
  	      const res = await Promise.race([fetchPro, ArticlesPage.timeout(ArticlesPage.TIMEOUT_SEC)])
  	      const data = await res.json()

  	      if (!res.ok) throw new Error(`${data.message} (${res.status})`)
  	      	return data
  	    } catch (error) { throw error }
  	  }
  	  
  		// 刪除 button 的事件處理器
  	  async deleteArticleHandler(e, btn, obj) {
  		  // 取得文章的 UUID
  		  const li = btn.parentElement
  	    const uuid = li.dataset.id
  	    const article = document.getElementById(uuid)
  	    
  	    // 顯示 Loading... 畫面
  	    obj.addElement()
  	    
  	    try {
  	      // 向後端發送 AJAX 請求並等待結果回傳,await=等待
  	      const result = await ArticlesPage.ajax({uuid: uuid})
  	     	
  	      if (result.response === ArticlesPage.HTTP_OK) {
  	    	console.log(result)
  	        li.remove()
  	        article.remove()
  	        obj.show(ArticlesPage.DELETE_MSG)
  	      } else {
  	        console.log(result)
  	      }
  	    } catch (error) {
  	      console.error(error)
	      } finally {
	        obj.removeElement()
	      }
  	  }
  		
  		// 將 Loading... 元素添加至 body 上面
  		addElement() {
  		  this.body.insertAdjacentHTML('beforeend', this.template)
  		}
  		
  	// 從 body 中移除 Loading... 元素
  		removeElement() {
  		  const modal = document.querySelector('.modal-backdrop')
  		  const spinner = document.querySelector('.spinner-border')
  		  
  		  this.body.removeChild(modal)
  		  this.body.removeChild(spinner)
  		}
  	}
  	
  	// Entry point
  	$(document).ready(() => {  	  
  		new ArticlesPage()
  	})
  </script>
</body>
</html>