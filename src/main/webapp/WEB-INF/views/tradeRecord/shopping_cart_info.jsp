<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>EASY LEARN - 購物車明細</title>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
body {
	background-color: rgb(243, 243, 241);
}

#amount {
	background-color: rgb(255, 255, 255);
}

img#sc_class_photo {
	background-color: rgb(255, 255, 255);
	padding: 5px;
	border: 1px rgb(0, 0, 0) solid;
}
</style>

<!-- JavaScript -->
<jsp:include page="../incloud/head-js.jsp" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />

	<div class="height100"></div>

	<!-- main start -->
	<div class="container  min-vh-100">
		<div class="row">
			<div class="col-sm-1"></div>
			<div id="sc_info" class="col-sm-8 mt-5">
				<h4 class="">
					<span aria-hidden="true" class="fa fa-shopping-cart"></span> 購物車 <small
						class="text-muted">總計 <a id="total">${shoppingCartList.size()}</a>
						堂課
					</small>
				</h4>
				<div class="row m-3 p-1">
					<div class="col-8">
						<a>課程:</a>
					</div>
					<div class="col-4">
						<a>價格:</a>
					</div>
				</div>
				<hr class="border-2 border-top border-black">
				<c:forEach var="item" items="${shoppingCartList}">
					<div id="item${item.getClassBean().getCid()}"
						class="row item m-3 p-1">
						<div class="col-2 d-inline-flex">
							<img id="sc_class_photo" width=100 height=100 src="https://iiiedug02.nilm.in/${item.getClassBean().getPhoto()}">
						</div>
						<div class="col-6 d-inline-flex">
							<h3 id="class_title" class="p-2">
								<a
									href="/SpecialTopic/viewClass/${item.getClassBean().getCid()}">${item.getClassBean().getTitle()}</a>
							</h3>
						</div>
						<div class="col-2 d-inline-flex">
							<h3 id="price${item.getClassBean().getCid()}" class="p-2">${item.getClassBean().getPrice()}</h3>
						</div>
						<input type="hidden" id="hidden_cid"
							value="${item.getClassBean().getCid()}" />
						<div class="col-2 p-2 d-inline-flex">
							<button type="button" class="btn btn-danger"
								id="sc_btn_${item.getClassBean().getCid()}"
								onclick="sc_del(${item.getClassBean().getCid()})">
								<i class="fa fa-trash fa-2x"></i>
							</button>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="col-sm-3 mt-5 mb-3">
				<div id="amount" class="shadow p-3">
					<h3>總記</h3>
					<h3 class="text-end">
						新台幣：<a id="sum">${ sum }</a>元
					</h3>
					<div class="text-end">
						<form id="idFormAioCheckOut" method="post"
							action="/SpecialTopic/ECPayServer">
							<input type="hidden" name="TotalAmount" id="TotalAmount" /> <input
								type="hidden" name="TradeDesc" id="TradeDesc" /> <input
								type="hidden" name="ItemName" id="ItemName" /> <input
								type="hidden" name="CidList" id="CidList" />

							<!-- Button trigger modal -->
							<button id="checkout_btn" type="button" class="btn btn-success"
								data-bs-toggle="modal" data-bs-target="#checkoutModal">
								結帳</button>

							<!-- Modal -->
							<div class="modal fade" id="checkoutModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h3 class="modal-title" id="exampleModalLabel">確認付款</h3>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											請確認購買的<font color="red">課程</font>及<font color="red">付款金額</font>，確定後將前往綠界付款API。
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary"
												onclick="checkout()">確認</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />


	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />


</body>
</html>