$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/SpecialTopic/getUsername",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data == null) {
                console.log("no data return");
            } else if (data.result != "null") {
                $('#LoginDiv').append("HI!! " + data.result);
                var btn = $('<button/>', {
                    text: '登出',
                    click: function () {
                        window.location = "/SpecialTopic/logout_page"
                    }
                });
                $('#LoginDiv').append(btn);
            } else {
                var lbtn = $('<button/>', {
                    text: '登入',
                    click: function () {
                        window.location = "/SpecialTopic/login_page"
                    }
                })
                var rbtn = $('<button/>', {
                    text: '註冊',
                    click: function () {
                        window.location = "/SpecialTopic/registerPage1"
                    }
                });
                $('#LoginDiv').append(lbtn);
                $('#LoginDiv').append(rbtn);
            }
        },
        error: function (xhr, status) {
            console.log("Error")
        }
    })
});