function register1() {
	var mb = {}
	mb["username"] = $("#username").val();
	mb["password"] = $("#password").val();
	mb["email"] = $("#email").val();
	mb["fullname"] = $("#fullname").val();
	mb["address"] = $("#address").val();
	mb["phone"] = $("#phone").val();
	mb["birthday"] = $("#birthday").val();
	mb["job"] = $("#job").val();
	mb["identitycard"] = $("#identitycard").val();
	mb["passportname"] = $("#passportname").val();
	$.ajax({
		type: "POST",
		url: "/SpecialTopic/registerAction1",
		dataType: "json",
		data: JSON.stringify(mb),
		contentType: "application/json",
		success: function(data) {
			if (data == null) {
				console.log("no data return");
			} else if (data.result == "ok") {
				window.location = "/SpecialTopic/"
			}
		}
	})
};
function check() {
	var username = $('input#username');
	var password = $('input#password');
	var repassword = $('input#repassword');
	
	if (password.val() != repassword.val()) {
		alert("兩次密碼輸入不一致");
		return false;
	}
	if (username.val() ===''){
		alert("帳號欄位不能空白!");
		return false;
	}
	if (password.val() ===''){
		alert("密碼欄位不能空白");
		return false;
	}
	
	if (!checkEmail()){
		return false;
	}
	$('form#registerform').submit();
	alert("成功註冊!");
}
function checkEmail() {
	var email = $('input#email').val();
	if (email == '') {
		alert('請輸入電子信箱');
		$('#vote_email').focus();
		return false;
	} else {
		var emailRegxp = /[\w-]+@([\w-]+\.)+[\w-]+/; //2009-2-12更正為比較簡單的驗證
		if (emailRegxp.test(email) != true) {
			alert('電子信箱格式錯誤');
			$('#vote_email').focus();
			$('#vote_email').select();
			return false;
		} else {
			return true;
		}
	}
}








