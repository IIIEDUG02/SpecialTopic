$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/SpecialTopic/countmember.controller",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
		$("span#countmember").attr("data-purecounter-end",data);
        },
        error: function (xhr, status) {
            console.log("Error")
        }
    })
});