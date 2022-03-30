function register1() {
	var mb = {}
	mb["username"]  = $("#username").val();
	mb["password"]  = $("#password").val();
	mb["email"]  =$("#email").val();
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
                window.location = "/SpecialTopic/registerPage2"
            } 
        }
    })
};


function register2() {
	
	var mdb = {};
	mdb["fullname"] = $("#fullname").val();
	mdb["address"] = $("#address").val();
	mdb["phone"] = $("#phone").val();
	mdb["birthday"] = $("#birthday").val();
	mdb["job"] = $("#job").val();
	mdb["identitycard"] = $("#identitycard").val();
	mdb["passportname"] = $("#passportname").val();
	
	
    $.ajax({
        type: "POST",
        url: "/SpecialTopic/registerAction2",
        dataType: "json",
        data: JSON.stringify(mdb),
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