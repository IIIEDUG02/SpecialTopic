<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>

<title>個人資料</title>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<!-- Templete JS -->
<jsp:include page="../incloud/body-js.jsp" />
<style>
h3 {
	margin: 15px;
}

.height100 {
	height: 100px;
}

.col {
	height: 670px;
	width: 600px;
}

div .username, div .password, div .fullname, div .phone, div .email, div .address,
	div .job, div .birthday, div .identitycard, div .gender {
	margin: 30px;
	font-size: 1.3em;
}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>

	<div class="container">
		<form action="/SpecialTopic/memberUpdateInformation" method="post">
			<div class="row">
				<div class="col-4">

					<img src="/SpecialTopic/img/register/tree.jpg">
				</div>
				<div class="col-8">

					<div class="row">
						<h3>個人資料</h3>
					</div>
					<div class="row">
						<div class="col-6">
							<div class="username">
								<div class="mb-3">
									<label for="formFile" class="form-label">上傳照片</label> <input
										class="form-control" type="file" id="formFile">
								</div>
								帳號: <a>${mb.getUsername()}</a> <input type="hidden"
									name="username" value="${mb.getUsername()}">
							</div>
							<div class="password">
								密碼: <a>************</a>
								<div class="input-group mb-3">
									<input id="password" type="hidden" name="password"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username" required="required"
										aria-describedby="button-addon2" value="${mb.getPassword()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
							<div class="fullname">
								姓名: <a>${mb.getMemberInformation().getFullname()}</a>
								<div class="input-group mb-3">
									<input id="fullname" type="hidden" name="fullname"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getFullname()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
							<div class="phone">
								手機: <a>${mb.getMemberInformation().getPhone()}</a>
								<div class="input-group mb-3">
									<input id="phone" type="hidden" name="phone"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getPhone()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
							<div class="email">
								信箱: <a>${mb.getMemberInformation().getEmail()}</a>
								<div class="input-group mb-3">
									<input id="email" type="hidden" name="email"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getEmail()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
						</div>
						<div class="col-6">
							<div class="address">
								地址: <a>${mb.getMemberInformation().getAddress()}</a>
								<div class="input-group mb-3">
									<input id="address" type="hidden" name="address"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getAddress()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
							<div class="job">
								工作: <a>${mb.getMemberInformation().getJob()}</a>
								<div class="input-group mb-3">
									<input id="job" type="hidden" name="job" class="form-control"
										placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getJob()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>
							<div id="publishDateMeta" class="birthday">
								生日:<a> <!-- 將文章發佈日期從 yyyy-mm-dd 轉成 yyyy/mm/dd --> <fmt:formatDate
										value="${mb.getMemberInformation().getBirthday()}"
										pattern="yyyy/MM/dd" />
								</a>
								<div class="input-group mb-3">
									<input id="check2" type="hidden" name="birthday"
										class="form-control" placeholder="Recipient's username"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getBirthday()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>

							</div>
							<div class="identitycard">
								身分證字號:<a>${mb.getMemberInformation().getIdentitycard()}</a>
								<div class="input-group mb-3">
									<input id="identitycard" type="hidden" name="identitycard"
										class="form-control" placeholder="請輸入身分證字號:"
										aria-label="Recipient's username"
										aria-describedby="button-addon2" required="required"
										value="${mb.getMemberInformation().getIdentitycard()}"
										onkeyup="this.value=this.value.replace(/\s+/g,'')">
									<button class="btn btn-outline-secondary" type="button"
										id="button-addon2">編輯</button>
								</div>
							</div>

							<div class="gender">
								性別: 男<a>${mb.getMemberInformation().getGender()}</a> <input
									id="gender" type="radio" name="gender" value=1 />女<input
									type="radio" name="gender" value=0 />
							</div>
							<input id="check" onclick="check3()" type="hidden"
								class="btn btn-success" value="確認"> <input id="check1"
								type="hidden" style="margin-left: 50px"
								class="btn btn-secondary" value="重置">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
	function editMemberInformation() {
		var pwdInput = $(this).parent().children("input");
		if (pwdInput.attr("name") == 'password') {
			pwdInput.val('');
		}
		$(this).parent().children("a").html("");
		$(this).parent().children("input").attr("type", "text");
		$(this).remove();
		$('input#check').attr("type", "submit");
		$('input#check1').attr("type", "reset");
		$('input#check2').attr("type", "date");
	}
	$('button').click(editMemberInformation);

	function check3() {
		
		var identitycard = $('input#identitycard')
		var reg = /^(([0-9]{3,4})|[0-9]{3,4}-)[0-9]{7,8}$/;
		
		if (!checkID(identitycard.val())) {
			return false;
		}		
		if (!reg.test($('input#phone').val())) {
			alert('電話號碼輸入有誤！');
			return false;
		}
		return false;
	}
	function checkID(idStr) {
		// 依照字母的編號排列，存入陣列備用。
		var letters = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X',
				'Y', 'W', 'Z', 'I', 'O');
		// 儲存各個乘數
		var multiply = new Array(1, 9, 8, 7, 6, 5, 4, 3, 2, 1);
		var nums = new Array(2);
		var firstChar;
		var firstNum;
		var lastNum;
		var total = 0;
		// 撰寫「正規表達式」。第一個字為英文字母，
		// 第二個字為1或2，後面跟著8個數字，不分大小寫。
		var regExpID = /^[a-z](1|2)\d{8}$/i;
		// 使用「正規表達式」檢驗格式
		if (idStr.search(regExpID) == -1) {
			// 基本格式錯誤
			alert("請仔細填寫身份證號碼");
			return false;
		} else {
			// 取出第一個字元和最後一個數字。
			firstChar = idStr.charAt(0).toUpperCase();
			lastNum = idStr.charAt(9);
		}
		// 	找出第一個字母對應的數字，並轉換成兩位數數字。
		for (var i = 0; i < 26; i++) {
			if (firstChar == letters[i]) {
				firstNum = i + 10;
				nums[0] = Math.floor(firstNum / 10);
				nums[1] = firstNum - (nums[0] * 10);
				break;
			}
		}
		// 執行加總計算
		for (var i = 0; i < multiply.length; i++) {
			if (i < 2) {
				total += nums[i] * multiply[i];
			} else {
				total += parseInt(idStr.charAt(i - 1)) * multiply[i];
			}
		}
		// 和最後一個數字比對
		if ((10 - (total % 10)) != lastNum) {
			alert("身份證號碼寫錯了！");
			return false;
		}
		return true;
	}
</script>
<!-- ======= Footer ======= -->
<jsp:include page="../incloud/footer-section.jsp" />
</html>