<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header" class="fixed-top">

	<div id="header2" class="container d-flex align-items-center">

		<h1 class="logo me-auto">
			<a href="/SpecialTopic/">OOXX線上教學平台</a>
		</h1>
		<nav id="navbar" class="navbar order-last order-lg-0">
			<ul>
				<li><a class="active" href="/SpecialTopic/">首頁</a></li>
				<li><a href="/SpecialTopic/about">關於我們</a></li>
				<li><a href="/SpecialTopic/courses">課程</a></li>
				<li><a href="/SpecialTopic/trainers">教師</a></li>
				<li><a href="/SpecialTopic/articles">知識補給站</a></li>
				<li><a href="contact">聯繫我們</a></li>
				<li id="dp" class="dropdown"><a href="#"><span>後台功能</span>
						<i class="bi bi-chevron-down"></i></a>
					<ul>
						<li class="dropdown"><a href="#"><span>金流</span> <i
								class="bi bi-chevron-right"></i></a>
							<ul>
								<li><a href="/SpecialTopic/tradeRecord/teacher">教師售課紀錄</a></li>
								<li><a href="#">Deep Drop Down 2</a></li>
								<li><a href="#">Deep Drop Down 3</a></li>
								<li><a href="#">Deep Drop Down 4</a></li>
								<li><a href="#">Deep Drop Down 5</a></li>
							</ul>
						<li class="dropdown"><a href="#"><span>Deep Drop
									Down</span> <i class="bi bi-chevron-right"></i></a>
							<ul>

								<li><a href="#">Deep Drop Down 1</a></li>
								<li><a href="#">Deep Drop Down 2</a></li>
								<li><a href="#">Deep Drop Down 3</a></li>
								<li><a href="#">Deep Drop Down 4</a></li>
								<li><a href="#">Deep Drop Down 5</a></li>
							</ul></li>
						<li><a href="#">Drop Down 2</a></li>
						<li><a href="#">Drop Down 3</a></li>
						<li><a href="#">Drop Down 4</a></li>
						<li><a href="/SpecialTopic/logout_page">登出</a></li>
					</ul></li>


			</ul>
			<i class="bi bi-list mobile-nav-toggle"></i>
		</nav>
		<!-- .navbar -->

		<button type="button" id="login" class="btn btn-primary" data-bs-target="login_page">登入</button> 
		
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">會員登入</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
        <button type="button" class="btn btn-primary">儲存</button>
      </div>
    </div>
  </div>
</div>
		
		<a id="register" href="registerPage1" class="get-started-btn">註冊</a>

	</div>
	<!-- 登入驗證畫面渲染 -->
	<script src="/SpecialTopic/js/loginjs.js"></script>
</header>
<!-- End Header -->
