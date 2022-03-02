function test() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/SpecialTopic/member",
        dataType: 'json', // 如果success沒有執行，可以先檢查dataType有沒有設對
        beforeSend: function(xhr) {
            xhr.withCredentials = true;
        },
        success: function(result, textStatus, jqXHR){   
            var cookies = jqXHR.getResponseHeader('Set-Cookie');
            console.log(cookies);
        }
    });
};

// $('#signUpBtn').click(
//     SignUp()
// );

function SignUp() {
    console.log("Start AJAX");
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/SpecialTopic/member/signup",
        data: {
            username: $("#username").val(),
            password: $.md5($("#password").val()),
            fullname: $("#fullname").val(),
            email: $("#email").val(),
            birthday: $("#birthday").val(),
            address: $("#address").val(),
            phone: $("#phone").val(),
            job: $("#job").val()
        },
        dataType: 'json', // 如果success沒有執行，可以先檢查dataType有沒有設對
        success: function (response) {
            alert(response.result);
        }
    });
    console.log("Complete AJAX");
}

// $('#signInBtn').click(
//     SignIn()
// );

function SignIn() {
    console.log("Start AJAX");
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/SpecialTopic/member/login",
        data: {
            username: $("#username").val(),
            password: $.md5($("#password").val()),
        },
        beforeSend: function(xhr) {
            xhr.withCredentials = true;
        },
        dataType: 'json', // 如果success沒有執行，可以先檢查dataType有沒有設對
        success: function(result, textStatus, jqXHR){   
            var cookies = jqXHR.getResponseHeader('Set-Cookie');
            console.log(cookies);
        },
    });
    console.log("Complete AJAX");
}