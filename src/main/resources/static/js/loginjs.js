$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/SpecialTopic/getLoginStatus",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data == null) {
                $( "li#dp" ).remove();
            } else if (data.username != "null") {
              $( "a#login" ).remove();
			  $( "a#register" ).remove();
            } else {
                $( "li#dp" ).remove();
            }
        },
        error: function (xhr, status) {
            console.log("Error")
        }
    })
});