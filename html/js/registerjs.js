function register1() {
	var mb = {}
	mb["username"]  = $("#username").val();
	mb["password"]  = $("#password").val();
	mb["email"]  =$("#email").val();
    $.ajax({
        type: "POST",
        url: "/registerAction1",
        dataType: "json",
        data: JSON.stringify(mb),
        contentType: "application/json",
        success: function (data) {
            if (data == null) {
                console.log("no data return");
            } else if (data.result == "ok") {
                window.location = "/registerPage2"
            } 
        }
    })
};


function register2() {
	console.log("456");
	var mdb = {};
	mdb["fullname"] = $("#fullname").val();
	mdb["address"] = $("#address").val();
	mdb["phone"] = $("#phone").val();
	mdb["birthday"] = $("#birthday").val();
	mdb["job"] = $("#job").val();
	
    $.ajax({
        type: "POST",
        url: "/registerAction2",
        dataType: "json",
        data: JSON.stringify(mdb),
        contentType: "application/json",
        success: function (data) {
            if (data == null) {
                console.log("no data return");
            } else if (data.result == "ok") {
                window.location = "/welcome"
            } 
        }
    })
};