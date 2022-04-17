<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<header id="header" class="fixed-top" style="z-index: 9999">

	<div id="header2" class="container d-flex align-items-center" >

		<h1 class="logo me-auto">
			<a href="/SpecialTopic/">EAZY LEARN</a>
		</h1>
		<nav id="navbar" class="navbar order-last order-lg-0">
			<ul>
				<li><a class="active" href="/SpecialTopic/">首頁</a></li>
				<li><a href="/SpecialTopic/about">關於</a></li>
				<li class="dropdown"><a href="#"><span>課程</span> <i
						class="bi bi-chevron-down"></i></a>
					<ul id="typeList">

					</ul></li>
				<li><a href="/SpecialTopic/trainers">教師</a></li>
				<li><a href="/SpecialTopic/articles">知識補給站</a></li>
				<li class="dropdown"><a href="contact">聯繫我們</a></li>
				<c:choose>
					<c:when test="${not empty pageContext.request.userPrincipal.name}">
						<li class="dropdown"><a href="#"> <span>後台功能</span><i
								class="bi bi-chevron-down"></i>
						</a>
							<ul>
								<li class="dropdown"><a href="/SpecialTopic/class/list"><span>課程管理</span></a>
								</li>
								<sec:authorize
									access="isAuthenticated() and hasRole('ROLE_admin')">
									<li><a href="/SpecialTopic/inputmain">學員資料統計</a></li>
									<li><a href="/SpecialTopic/getMoney">銷售額統計</a></li>
									<li class="dropdown"><a href="#"><span>熱門課程管理 </span>
											<i class="bi bi-chevron-right"></i></a>
										<ul>
											<li><a href="/SpecialTopic/ypclasscontrolltop5">年度熱門課程</a></li>
											<li><a href="/SpecialTopic/mpclasscontrolltop5">當月熱門課程</a></li>
										</ul></li>
								</sec:authorize>
								<li><a href="/SpecialTopic/member/editInformation"
									type="button">個人資料</a></li>
								<sec:authorize
									access="hasRole('ROLE_admin') and isAuthenticated()">
									<li><a href="/SpecialTopic/member/membermanage"
										type="button">學員管理</a></li>
								</sec:authorize>
								<li><a href="/SpecialTopic/logout_page">登出</a></li>
							</ul> <i class="bi bi-list mobile-nav-toggle"></i></li>
						<li id="scl"></li>
						<li><img id="mbphoto123" src="" alt="預覽圖片"
							style="width: 30px; height: 30px; border-radius: 50%; margin-left: 20px"></li>
						<script>getMemberPhoto();getShoppingCartList();	</script>
					</c:when>
				</c:choose>

			</ul>
		</nav>
		<!-- .navbar -->

		<c:choose>
			<c:when test="${empty pageContext.request.userPrincipal.name}">
				<!-- Button trigger modal -->
				<a type="button" id="login" class="get-started-btn"
					data-bs-toggle="modal" data-bs-target="#loginform">登入</a>
				<!-- Modal -->
				<div class="modal fade" id="loginform" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Header -->
							<div class="modal-header"
								style="background-color: hsla(89, 43%, 51%, 0.3)">
								<h5 class="modal-title" id="exampleModalLabel">登入</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<!-- Body -->
							<div class="modal-body"
								style="background-color: hsla(89, 43%, 51%, 0.3)">
								<form action="/SpecialTopic/login" method="POST">
									<!-- username -->
									<div class="form-group">
										<input type="text" name="username" required="required"
											class="username form-control" placeholder="帳號: ">

									</div>
									<!-- passowrd -->
									<div class="form-group">
										<input type="password" name="password" required="required"
											class="password form-control" placeholder="密碼: ">

									</div>
									<!-- checkbox -->
									<div class="form-group">
										<input type="checkbox" id="rememberMe-key"
											name="rememberMe-key" class="remember">記住我的密碼 <a
											href="registerPage1" type="button" class="memberregister"
											style="float: right; font-size: 20px">點擊註冊</a> <span
											style="float: right; font-size: 20px">尚未成為學員?</span>
									</div>
									<!-- 送出按鈕 -->
									<div>
										<button type="submit" class="btn btn-primary">登入</button>
									</div>
								</form>

							</div>
							<!-- Footer -->
							<div class="modal-footer"
								style="background-color: hsla(89, 43%, 51%, 0.3)">
								<div class="signup"></div>

							</div>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
	</div>
</header>
<!-- End Header -->
<!-- 購物車 -->
<div class="shopping-cart shadow">
	<!-- 購物車清單 -->
	<ul class="shopping-cart-items">

	</ul>
	<!-- 結帳 -->
	<a class="btn btn-success checkout"
		href="/SpecialTopic/ShoppingCart/getInfo" class="button">結帳</a>
</div>

