$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/SpecialTopic/countclass",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
		$('span#countclass').attr("data-purecounter-end") = data;
        },
        error: function (xhr, status) {
            console.log("Error")
        }
    })
});