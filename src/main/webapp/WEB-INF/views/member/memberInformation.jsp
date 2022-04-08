<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<title>�ӤH���</title>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<style>

h3{
	margin:15px;
}
	
.height100 {
	height: 100px;
}

.col {
	height: 500px;
	padding: 70px;
}


/* .main-box { */
/* 	margin: 70px auto; */
/* 	width: 400px; */
/* 	height: 540px; */
/* 	padding: 50px; */
 	/*	box-shadow: 5px 5px 10px #999;*/ 
/* 	border: 1px solid #fff text-align:center; */
/* 	font-size: 1.0em; */
}

.username, .password, .fullname, .phone, .email, .address, 
.job {
	margin: 30px;
	height: 20px;
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
				<!--  	<div class="main-box"> -->
				<div>
					<h3 class="h3">�ӤH���</h3>
				</div>
				<form action="/SpecialTopic/memberUpdateInformation" method="post">
					<div class="username">
						�b��: <a>${mb.getUsername()}</a>
					</div>
					<div>
						<input type="hidden" name="username" value="${mb.getUsername()}">
					</div>
					<div class="password">
						�K�X: <a>************</a> <input type="hidden" name="password"
							value="${mb.getPassword()}">
						<button>�s��</button>
					</div>
					<div class="fullname">
						�m�W: <a>${mb.getMemberInformation().getFullname()}</a> <input
							type="hidden" name="fullname"
							value="${mb.getMemberInformation().getFullname()}">
						<button>�s��</button>
					</div>
					<div class="phone">
						���: <a>${mb.getMemberInformation().getPhone()}</a> <input
							type="hidden" name="phone"
							value="${mb.getMemberInformation().getPhone()}">
						<button>�s��</button>
					</div>
					<div class="email">
						�H�c: <a>${mb.getMemberInformation().getEmail()}</a> <input
							type="hidden" name="email"
							value="${mb.getMemberInformation().getEmail()}">
						<button>�s��</button>
					</div>
					<div class="address">
						�a�}: <a>${mb.getMemberInformation().getAddress()}</a> <input
							type="hidden" name="address"
							value="${mb.getMemberInformation().getAddress()}">
						<button>�s��</button>
					</div>
					<div class="job">
						�u�@: <a>${mb.getMemberInformation().getJob()}</a> <input
							type="hidden" name="job"
							value="${mb.getMemberInformation().getJob()}">
						<button>�s��</button>
					</div>
					<input id="check" type="hidden" value="�T�{">
				</form>
			</div>
		<div class="col"><img src="/SpecialTopic/img/register/tree.jpg"></div>
		</div>
	</div>
	<!--  	</div> -->
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
	}

	$('button').click(editMemberInformation);
</script>
</html>