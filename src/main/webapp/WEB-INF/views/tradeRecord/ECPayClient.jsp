<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>綠界付款頁面</title>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="keywords">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs" data-aos="fade-in"></div>
	<!-- End Breadcrumbs -->
	<div class="container my-5 border shadow p-5 w-75">

		<form id="idFormAioCheckOut" method="post" action="ECPayServer"
			class="border shadow p-3 mb-5">
			<div class="row text-primary ">
				<div class="mb-3 col-sm-3">
					<label class="col-xs-12">TotalAmount(交易金額):</label> <input
						type="text" name="TotalAmount" value="1050" class="form-control" />
					請帶整數，不可有小數點。僅限新台幣。
				</div>
				<div class="mb-3 col-sm-4">
					<label class="col-xs-12">TradeDesc(交易描述): </label> <input
						type="text" name="TradeDesc" value="刷兩三下就光亮如新"
						class="form-control" /> 請勿帶入特殊字元。
				</div>
				<div class="mb-3 col-sm-5">
					<label class="col-xs-12">ItemName(商品名稱):</label> <input type="text"
						name="ItemName" value="馬桶刷850元x1#消毒水200元" class="form-control" />
					商品名稱<br /> 1. 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。 2.
					商品名稱字數限制為中英數 400 字內，超過此限制系統將自動截斷。
				</div>
			</div>

			<button type="submit" class="btn btn-primary">結帳</button>
		</form>

		<div class="row">
			<div class="mb-3 col-sm-12">
				<h5>(Server端)測試環境前置準備事項：</h5>
				<hr />
				1. 特店編號(MerchantID)：2000132<br /> 2. ALL IN ONE介接
				HashKey：5294y06JbISpM5x9<br /> 3. ALL IN ONE介接
				HashIV：v77hoKGq4kWxNNIS<br /> (上列三項設定於伺服端 payment_conf.xml)
				<hr />
				4. MerchantTradeNo：特店交易編號(由特店提供)。 特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。<br />
				5. MerchantTradeDate：特店交易時間。格式為：yyyy/MM/dd HH:mm:ss<br />
				(上列二項由伺服端以程式產出)<br />
				<hr />
				6. PaymentType：交易類型。請固定填入aio。<br /> (上列設定於伺服端
				~.domain.AioCheckOutALL.java之PaymentType實體變數)<br /> 7.
				ChoosePayment:選擇預設付款方式。<br />
				綠界提供下列付款方式:Credit:信用卡及銀聯卡(需申請開通)；WebATM:網路ATM；ATM:自動櫃員機；CVS:超商代碼；BARCODE:超商條碼；ALL:不指定付款方式，由綠界顯示付款方式選擇頁面<br />
				(上列設定於伺服端 ~.domain.AioCheckOutALL.java之ChoosePayment實體變數)<br />
				<hr />
				付款結果通知我們伺服端的方式(可二選一)：<br /> 8.
				ReturnURL：付款完成通知回傳網址。當消費者付款完成後，綠界會將付款結果參數以幕後(Server
				POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。<br /> 9.
				OrderResultURL：付款完成通知回傳網址。當消費者付款完成後，綠界會將付款結果參數以幕前(Client
				POST)回傳到該網址。<br /> (上列二項直接設定於伺服端程式)
				<hr />
				(若9與X同時設定，將會以9為主。)<br /> X.
				ClientBackURL：設定此選項會使綠界付款成功畫面增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址。(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)<br />
				(上列直接設定於伺服端程式)<br />
				<hr />
				信用卡測試卡號：4311-9522-2222-2222 (注意事項：只有此組卡號可測試交易成功流程)<br />
				信用卡測試安全碼：222<br /> 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，例如在
				2016/04/20 當天作測試，請設定05/2016(含)之後的有效月年。<br />
				<hr />
				廠商管理後台登入帳號/密碼：stagetest1234/test1234<br />
				廠商管理後台測試環境：https://vendor-stage.ecpay.com.tw(此網站可提供：1. 查詢 ecpay訂單，2.
				模擬付款並通知特店)<br />
				<hr />
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