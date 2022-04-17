function showLoginForm() {
	$('div#loginform').modal('toggle');
}

function getMemberPhoto() {
	$.ajax({
		type: "get",
		url: "/SpecialTopic/getMemberPhoto",
		success: function(data) {
			if (data != null) {
				console.log(data);
				
				$('#mbphoto123').attr('src',data);
			}
		},
		error: function(xhr, status) {
			console.log(xhr);
			console.log(status);
		}
	})
}
