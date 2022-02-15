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
	let jqxhr = $.getJSON({
		type: 'GET',
		url: "/SpecialTopic/LoginAuth",
		data: {
			username: $("input#usernametext").val(),
			password: $("input#passwordtext").val()
		}
	}).done(function(resp) {
		//alert('成功');
		alert(resp.result);
	}).fail(function() {
		//alert('失敗');
	}).always(function() {
		//alert('結束');
	});
	console.log("Login");
});