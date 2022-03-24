<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>新增文章</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="../assets/img/favicon.png" rel="icon">
<link href="../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
  href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
  rel="stylesheet">
<link rel='stylesheet'
  href='https://fonts.googleapis.com/icon?family=Material+Icons'>

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<link href="/SpecialTopic/css/multi-select.css" rel="stylesheet">

<!-- 匯入 summernote 的 css 檔案 -->
<link
  href="/SpecialTopic/assets/vendor/summernote/css/summernote-lite.css"
  rel="stylesheet">

<style>
html {
  font-size: 10px;
}

body {
  font-family: "PingFang TC", 微軟正黑體, sans-serif;
}

.tags-container, .textarea-container {
  margin-top: 34px !important;
}
</style>
</head>

<body>

  <!-- ======= Header ======= -->
  <jsp:include page="../incloud/header-section.jsp" />

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
      <div class="container">
        <h2>發佈文章</h2>
        <p>在此頁面，您可以新增您的文章，分享您的生活、知識或所見所聞。</p>
      </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">

        <div class="row mt-5">

          <div class="col-lg-4">
            <div class="info">
              <div class="address">
                <i class="bi bi-book"></i>
                <h4>文章標題:</h4>
                <p>為您的文章新增一個合適的標題</p>
              </div>

              <div class="email">
                <i class="bi bi-hash"></i>
                <h4>文章標籤:</h4>
                <p>可以為文章添加多個標籤</p>
              </div>

              <div class="phone">
                <i class="bi bi-file-earmark-text"></i>
                <h4>文章內容:</h4>
                <p>文章內容目前只能新增純文字</p>
              </div>

            </div>

          </div>

          <div class="col-lg-8 mt-5 mt-lg-0">

            <form id="createArticleForm" action="create" method="post"
              role="form" class="php-email-form">
              <div class="row">
                <div class="col-md-6 form-group">
                  <input type="text" id="title" name="title"
                    class="form-control" id="title"
                    placeholder="請輸入文章標題" required>
                </div>
              </div>

              <div class="form-group col-md-6 tags-container">
                <select id="tags" name="tags"
                  class="form-group mt-4 col-md-4" multiple="multiple"
                  required>
                  <c:forEach var="tag" items="${tags}">
                    <option value="${tag.getId()}">${tag.getName()}</option>
                  </c:forEach>
                </select>
              </div>

              <div class="form-group mt-3 textarea-container">
                <div id="summernote" name="content"></div>
              </div>
              <div class="my-3">
                <div class="loading">新增中...</div>
                <div class="error-message"></div>
              </div>
              <div class="text-center">
                <button id="createArticleButton" type="submit">發佈文章</button>
              </div>
            </form>
          </div>

        </div>

      </div>
    </section>
    <!-- End Contact Section -->

  </main>
  <!-- End #main -->

  <!-- ======= Footer ======= -->
  <jsp:include page="../incloud/footer-section.jsp" />

  <!-- Toast -->
  <jsp:include page="toast.jsp" />

  <!-- Template Main JS File -->
  <jsp:include page="../incloud/body-js.jsp" />
  <script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  <script src="/SpecialTopic/js/multi-select.js"></script>

  <!-- 匯入 summernote 的 js 檔案 -->
  <script
    src="/SpecialTopic/assets/vendor/summernote/js/summernote-lite.js"></script>

  <!-- Toast js -->
  <script src="/SpecialTopic/js/toast.js"></script>

  <script>
  $(document).ready(() => {
		// 初始化 summmernote 套件
  	$('#summernote').summernote({
        tabsize: 2,
        height: 300
    })
    
  	const form = document.querySelector('#createArticleForm')
  	const btn = document.querySelector('#createArticleButton')
  	const select = new drop({
      selector: '#tags',
    })
 		
    function displayError(error) {
  		form.querySelector('.loading').classList.remove('d-block')
  		showToast(error)
	  }
  	
		/*
			驗證發佈文章的表單是否有效後才送出，否則顯示提示訊息。
			return 值為 boolean，true 表示表單驗證成功，反之。
		*/
  	function isFormValid(form) {
  		"use strict";

  		const title = form.querySelector('#title')
  		const tags = form.querySelector('#tags')
  		const content = form.querySelector('#content')
  		const editable = form.querySelector('.note-editable')
  		
			content.value = editable.innerHTML
			form.querySelector('.loading').classList.add('d-block');
  		form.querySelector('.error-message').classList.remove('d-block');
      
      if (title.value.trim().length == 0) {
      	displayError('請輸入文章標題！')
      	return false
      }
      	
      if (![...tags.options].some(e => e.selected)) {
      	displayError('請選擇至少一種分類標籤！')
      	return false
      }
      	
      if (content.value.trim().length == 0) {
      	displayError('請輸入文章內容！')
      	return false
      }
      
      return true
  	}
  	
		// Submit button click event.
  	btn.addEventListener('click', function(e) {
  		e.preventDefault()
  		
  		// 表單驗證成功後才送出
  		if (isFormValid(form))
				form.submit()
  	})
  })
  </script>
</body>

</html>