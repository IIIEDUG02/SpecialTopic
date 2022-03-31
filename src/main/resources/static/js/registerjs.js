function register1() {
	var mb = {}
	mb["username"]  = $("#username").val();
	mb["password"]  = $("#password").val();
	mb["email"]  =$("#email").val();
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
        success: function (data) {
            if (data == null) {
                console.log("no data return");
            } else if (data.result == "ok") {
                window.location = "/SpecialTopic/"
            } 
        }
    })
};
