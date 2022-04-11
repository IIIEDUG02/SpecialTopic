$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "/SpecialTopic/ypclassfindtop5",
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			for (i = 0; i <= 2; i++) {
				var i_bxuser = $("<i class='bx bx-user'></i>");
				var i_bxheart = $("<i class='bx bx-heart'></i>");
				var div_style = $('<div class="trainer-rank d-flex align-items-center"></div>');
				div_style.append(i_bxuser);
				div_style.append('&nbsp;' + 50 + '&nbsp;&nbsp;');
				div_style.append(i_bxheart);
				div_style.append('&nbsp;' + 87 + '&nbsp;&nbsp;');

				var img_teacher = $("<img src='assets/img/teacher.png' class='img-fluid' alt='待補' />");
				img_teacher.addClass("img-fluid");
				var span_teacherName = $('<span>' + data[i]["teacher"]["fullname"] + '</span>');
				var div_teacherinfo = $('<div class="trainer-profile d-flex align-items-center">');
				div_teacherinfo.append(img_teacher);
				div_teacherinfo.append(span_teacherName);

				var div_teacherAndstyle = $('<div class="trainer d-flex justify-content-between align-items-center"></div>');
				div_teacherAndstyle.append(div_teacherinfo);
				div_teacherAndstyle.append(div_style);

				var h3_title = $('<h3><a href="course-details.html">' + data[i]['class']["title"] + '</a></h3>')
				var p_de = $('<p>' + data[i]["class"]["classDetailsBean"]["descript"] + '</p>')

				var h4_classtype = $("<h4>" + data[i]["class"]["classType"] + "</h4>");
				var p_price = $(' <p class="price">' + data[i]["class"]["price"] + '</p>');
				var div_typeAndPrice = $('<div class="d-flex justify-content-between align-items-center mb-3"></div>');
				div_typeAndPrice.append(h4_classtype);
				div_typeAndPrice.append(p_price);

				var div_course_content = $('<div class="course-content"></div>');
				div_course_content.append(div_typeAndPrice);
				div_course_content.append(h3_title);
				div_course_content.append(p_de);
				div_course_content.append(div_teacherAndstyle);

				var img_course_image = $('<img src=' + "assets/img/class.PNG" + ' class="img-fluid" alt="待補圖" >');
				var div_courseitem = $('<div class="course-item"></div>');
				div_courseitem.append(img_course_image);
				div_courseitem.append(div_course_content);

				var div_topx = $('<div class="col-lg-4 col-md-6 d-flex align-items-stretch"></div>');
				div_topx.append(div_courseitem);

				$('div#ypclass').append(div_topx);
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
});













