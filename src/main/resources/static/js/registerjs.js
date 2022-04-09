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
	var re1 = /\s|[　]/g;  //[]裡面是一個全型空白
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


//	var identitycard = $('input#identitycard')
//	if (!checkID(identitycard.val())) {
//		return false;
//	}
	

//	var reg = /^(([0-9]{3,4})|[0-9]{3,4}-)[0-9]{7,8}$/;
//    if(!reg.test($('input#phone').val())){
//        alert('電話號碼輸入有誤！');
//		return false;
//    }



//function checkID(idStr) {
//	// 依照字母的編號排列，存入陣列備用。
//	var letters = new Array('A', 'B', 'C', 'D',
//		'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
//		'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
//		'X', 'Y', 'W', 'Z', 'I', 'O');
//	// 儲存各個乘數
//	var multiply = new Array(1, 9, 8, 7, 6, 5,
//		4, 3, 2, 1);
//	var nums = new Array(2);
//	var firstChar;
//	var firstNum;
//	var lastNum;
//	var total = 0;
//	// 撰寫「正規表達式」。第一個字為英文字母，
//	// 第二個字為1或2，後面跟著8個數字，不分大小寫。
//	var regExpID = /^[a-z](1|2)\d{8}$/i;
//	// 使用「正規表達式」檢驗格式
//	if (idStr.search(regExpID) == -1) {
//		// 基本格式錯誤
//		alert("請仔細填寫身份證號碼");
//		return false;
//	} else {
//		// 取出第一個字元和最後一個數字。
//		firstChar = idStr.charAt(0).toUpperCase();
//		lastNum = idStr.charAt(9);
//	}
	// 找出第一個字母對應的數字，並轉換成兩位數數字。
//	for (var i = 0; i < 26; i++) {
//		if (firstChar == letters[i]) {
//			firstNum = i + 10;
//			nums[0] = Math.floor(firstNum / 10);
//			nums[1] = firstNum - (nums[0] * 10);
//			break;
//		}
//	}
//	// 執行加總計算
//	for (var i = 0; i < multiply.length; i++) {
//		if (i < 2) {
//			total += nums[i] * multiply[i];
//		} else {
//			total += parseInt(idStr.charAt(i - 1)) *
//				multiply[i];
//		}
//	}
//	// 和最後一個數字比對
//	if ((10 - (total % 10)) != lastNum) {
//		alert("身份證號碼寫錯了！");
//		return false;
//	}
//	return true;
//}



