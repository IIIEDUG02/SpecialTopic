/**
 * 用來登入驗證使用 By 1+
 */
$("input#login").click(function() {
	if ($("input#usernametext").val() == "") {
		alert("請輸入帳號");
		return;
	}
	if ($("input#passwordtext").val() == "") {
		alert("請輸入密碼");
		return;
	}
	$.ajax({
		type: 'POST',
		url: "SpecialTopic/LoginAuth",
		data: {
                username: $("input#usernametext").val(),
                password: $("input#passwordtext").val()
            },
		success: function(result)
            {
                console.log("Sussesed!!");
            }
	});


	console.log("Login");
});