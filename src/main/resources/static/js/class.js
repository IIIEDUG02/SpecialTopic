

$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/SpecialTopic/allonlineclass",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			var count = Object.keys(data).length;
			for (var i = 0; i < count; i++) {
		
				var p_price = $("<p class='price'> $"+data[i]['price']+"</p>");
				var h4_type = $("<h4>"+data[i]['classType']+"</h4>");
				var div_class_detail = $("<div class='d-flex justify-content-between align-items-center mb-3'>")

				div_class_detail.append(h4_type);
				div_class_detail.append(p_price);

				var h3_title = $("<h3><a href='#'>"+data[i]['title']+"</a></h3>");
				var div_class_content = $("<div class='course-content'>");

				div_class_content.append(div_class_detail);
				div_class_content.append(h3_title);

				var img_class = $("<img src='"+data[i]['photo']+"' class='img-fluid' alt='...'>");
				var div_class_item = $("<div class='course-item'>");

				div_class_item.append(img_class);
				div_class_item.append(div_class_content);

				var div_class_view = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'>");

				div_class_view.append(div_class_item);

				var div_row = $("div#row");	
						
				div_row.append(div_class_view);
			};
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
});