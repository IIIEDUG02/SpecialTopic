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
	//檢查輸入兩次密碼是否一致
	if (password.val() != repassword.val()) {
		swal("錯誤","兩次密碼輸入不一致","error",{botton:"OK!"});
		return false;
	}
	//檢查欄位是否空白
	if (username.val() ===''){
		swal("錯誤","帳號欄位不能空白!","error",{botton:"OK!"});
		return false;
	}
	if (password.val() ===''){
		swal("錯誤","密碼欄位不能空白","error",{botton:"OK!"});
		return false;
	}
	//檢查信箱格式
	if (!checkEmail()){
		return false;
	}
	$('form#registerform').submit();
	swal("GoodJob!","註冊成功!","success");
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
			swal('錯誤','電子信箱格式錯誤','error',{botton:"OK!"});
			$('#vote_email').focus();
			$('#vote_email').select();
			return false;
		} else {
			return true;
		}
	}
}








