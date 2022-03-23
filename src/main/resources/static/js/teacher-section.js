$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/SpecialTopic/ypteacherfindtop5",
		contentType: "application/json",
		dataType: "json",
		success: function(data) {

			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);

			var p_phone = $("<p>" + data[0]["phone"] + "</p>");
			var span_job = $("<span>" + data[0]["job"] + "</span>");
			var h4_name = $("<h4>" + data[0]["fullname"] + "</h4>");

			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(h4_name);
			div_member_content.append(span_job);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			var img_photo = $("<img src='" + data[0]["photo"] + "' class='img-fluid' alt=''>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#trainers').append(div_class);

		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
});













