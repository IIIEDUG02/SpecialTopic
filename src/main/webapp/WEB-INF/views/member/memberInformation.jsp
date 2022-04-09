<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/SpecialTopic/js/registerjs.js"></script>
<title>個人資料</title>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
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
		<div class="row">
			<div class="col">
				<img src="/SpecialTopic/img/register/tree.jpg">
			</div>
			<div class="col">
				<!--  	<div class="main-box"> -->
				<div>
					<h3 class="h3">個人資料</h3>
				</div>
				<form action="/SpecialTopic/memberUpdateInformation" method="post">
					<div class="username">
						<div class="mb-3">
							<label for="formFile" class="form-label">上傳照片</label> 
							<input class="form-control" type="file"
								id="formFile">
						</div>
						帳號: <a>${mb.getUsername()}</a> <input type="hidden"
							name="username" value="${mb.getUsername()}">
					</div>
					<div class="password">
						密碼: <a>************</a>
						<div class="input-group mb-3">
							<input type="hidden" name="password" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2" value="${mb.getPassword()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>
					<div class="fullname">
						姓名: <a>${mb.getMemberInformation().getFullname()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="fullname" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getFullname()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>
					<div class="phone">
						手機: <a>${mb.getMemberInformation().getPhone()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="phone" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getPhone()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>
					<div class="email">
						信箱: <a>${mb.getMemberInformation().getEmail()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="email" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getEmail()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col">
				<form action="/SpecialTopic/memberUpdateInformation" method="post">
					<div class="address">
						地址: <a>${mb.getMemberInformation().getAddress()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="address" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getAddress()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>
					<div class="job">
						工作: <a>${mb.getMemberInformation().getJob()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="job" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getJob()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>

					<div class="birthday">
						生日:<input type="date" name="birthday" required="required" />
					</div>

					<div class="identitycard">
						身分證字號:<a>${mb.getMemberInformation().getIdentitycard()}</a>
						<div class="input-group mb-3">
							<input type="hidden" name="identitycard" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"
								value="${mb.getMemberInformation().getIdentitycard()}"
								onkeyup="this.value=this.value.replace(/\s+/g,'')">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">編輯</button>
						</div>
					</div>

					<div class="gender">
						性別: 男<input type="radio" name="gender" value=1 />女<input
							type="radio" name="gender" value=0 />
					</div>
				</form>
				<input id="check" type="hidden" class="btn btn-success" value="確認">
				<input id="check1" type="hidden" style="margin-left: 50px"
					class="btn btn-secondary" value="重置">
			</div>
		</div>
	</div>
	<!-- ======= Footer ======= -->
	<jsp:include page="../incloud/footer-section.jsp" />
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
	}

	$('button').click(editMemberInformation);
</script>
</html>