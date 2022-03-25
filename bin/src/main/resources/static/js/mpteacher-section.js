$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/SpecialTopic/mpteacherfindtop5",
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			
			//Top1
			
			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);
			
			//content
			var rank = $("<h1>TOP1</h1>")
			var p_phone = $("<p>" +"Phone:"+ data[0]["phone"] + "</p>");
			var span_email = $("<span>" + "Email:"+ data[0]["email"] + "</span>");
			var h4_name = $("<h4>" + data[0]["fullname"] + "</h4>");
			
			//顯示
			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(rank);
			div_member_content.append(h4_name);
			div_member_content.append(span_email);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			//<img src='data[0]["photo"]' class='img-fluid' alt=''>
			var img_photo = $("<img src='" + data[0]["photo"] + "' class='img-fluid' alt='待補圖'>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#mpteacher').append(div_class);
			
			//Top2
			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);

			var rank = $("<h1>TOP2</h1>")
			var p_phone = $("<p>" +"Phone:"+ data[1]["phone"] + "</p>");
			var span_email = $("<span>" + "Email:"+ data[1]["email"] + "</span>");
			var h4_name = $("<h4>" + data[1]["fullname"] + "</h4>");
			
			//顯示
			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(rank);
			div_member_content.append(h4_name);
			div_member_content.append(span_email);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			//<img src='data[0]["photo"]' class='img-fluid' alt=''>
			var img_photo = $("<img src='" + data[1]["photo"] + "' class='img-fluid' alt='待補圖'>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#mpteacher').append(div_class);
			
			//Top3
			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);

			var rank = $("<h1>TOP3</h1>")
			var p_phone = $("<p>" +"Phone:"+ data[2]["phone"] + "</p>");
			var span_email = $("<span>" + "Email:"+ data[2]["email"] + "</span>");
			var h4_name = $("<h4>" + data[2]["fullname"] + "</h4>");
			
			//顯示
			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(rank);
			div_member_content.append(h4_name);
			div_member_content.append(span_email);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			//<img src='data[0]["photo"]' class='img-fluid' alt=''>
			var img_photo = $("<img src='" + data[2]["photo"] + "' class='img-fluid' alt='待補圖'>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#mpteacher').append(div_class);
			
			//Top4
			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);
			
			var rank = $("<h1>TOP4</h1>")
			var p_phone = $("<p>" +"Phone:"+ data[3]["phone"] + "</p>");
			var span_email = $("<span>" + "Email:"+ data[3]["email"] + "</span>");
			var h4_name = $("<h4>" + data[3]["fullname"] + "</h4>");
			
			//顯示
			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(rank);
			div_member_content.append(h4_name);
			div_member_content.append(span_email);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			//<img src='data[0]["photo"]' class='img-fluid' alt=''>
			var img_photo = $("<img src='" + data[3]["photo"] + "' class='img-fluid' alt='待補圖'>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#mpteacher').append(div_class);
			
			//Top5
			var a_twitter = $("<a href='#'><i class='bi bi-twitter'></i></a>");
			var a_facebook = $("<a href='#'><i class='bi bi-facebook'></i></a>");
			var a_instagram = $("<a href='#'><i class='bi bi-instagram'></i></a>");
			var a_linkedin = $("<a href='#'><i class='bi bi-linkedin'></i></a>");

			var div_social = $("<div class='social'></div>");
			div_social.append(a_twitter);
			div_social.append(a_facebook);
			div_social.append(a_instagram);
			div_social.append(a_linkedin);
			
			var rank = $("<h1>TOP5</h1>")
			var p_phone = $("<p>" +"Phone:"+ data[4]["phone"] + "</p>");
			var span_email = $("<span>" + "Email:"+ data[4]["email"] + "</span>");
			var h4_name = $("<h4>" + data[4]["fullname"] + "</h4>");
			
			//顯示
			var div_member_content = $("<div class='member-content'></div>");
			div_member_content.append(rank);
			div_member_content.append(h4_name);
			div_member_content.append(span_email);
			div_member_content.append(p_phone);
			div_member_content.append(div_social);

			//<img src='data[0]["photo"]' class='img-fluid' alt=''>
			var img_photo = $("<img src='" + data[4]["photo"] + "' class='img-fluid' alt='待補圖'>");

			var div_member = $("<div class=\"member\"></div>")
			div_member.append(img_photo);
			div_member.append(div_member_content);

			var div_class = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'></div>")
			div_class.append(div_member);

			$('div#mpteacher').append(div_class);
			


		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
});













