<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>${article.getTitle()}</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Head CSS -->
  <jsp:include page="../incloud/head-css.jsp" />
  
  <!-- import CSS -->
<link rel="stylesheet" href="/SpecialTopic/css/retrieve-article.css" />
</head>

<body>
  <!-- ======= Header ======= -->
  <jsp:include page="../incloud/header-section.jsp" />
  
  <div class="container">
    <div class="main" style="margin-top: 100px; margin-bottom: 20px;">
      <div class="kTfCsX">
        <div class="sc-1avfetc-0 iqvROh sc-vynlzo-7 bLoMCB">
          <a href="articles">知識補給站</a>
          <span class="sc-1avfetc-1 khSOso">&gt;</span>
          <a href="articles">${article.getTags().toArray()[0].getName()}</a>
        </div>
        
        <div class="yJnUL">
          <div class="kiwyhU">
            <div class="d-flex">
              <!-- authorMeta -->
              <div id="authorMeta" class="author-meta">
                <div class="author-meta__author"></div>
                ${article.getMember().getUsername()}
              </div>
  
              <!-- publishDateMeta -->
              <div id="publishDateMeta" class="publish-date-meta">
                <img src="img/articles/calendar-icon.svg" 
                class="publish-date-meta__img"> 
                <fmt:formatDate value="${article.getPublishTime()}" pattern="yyyy/MM/dd"/>
              </div>
  
              <!-- viewCountMeta -->
              <div id="viewCountMeta" class="view-count-meta">
                <img src="img/articles/eye-icon.svg" alt="" class="view-count-meta__img"> ${article.getPageViews()}
              </div>
            </div>
          </div>
          
          <h1 class="dThJVG">${article.getTitle()}</h1>
        </div>
      
        ${article.getContent()}
      </div>
    </div>
  </div>

  <!-- ======= Footer ======= -->
  <jsp:include page="../incloud/footer-section.jsp" />

  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <!-- jQuery -->
  <script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
  
  <!-- Templete JS -->
  <jsp:include page="../incloud/body-js.jsp" />
</body>

</html>