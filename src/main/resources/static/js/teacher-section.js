$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/SpecialTopic/ypteacherfindtop5",
        dataType: "json",
        success: function (data) {
			console.log(data)
			
			
        },
        error: function (xhr, status) {
            console.log("Error")
        }
    })
});