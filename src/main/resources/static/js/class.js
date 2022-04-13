// 取的所有課程清單
//$(document).ready(function() {
//	$.ajax({
//		type: "GET",
//		url: "/SpecialTopic/class/allonline",
//		dataType: "json",
//		contentType: "application/json",
//		success: function(data) {
//			var count = Object.keys(data).length;
//			for (var i = 0; i < count; i++) {
//				
//				
//				
//				var p_price = $("<p class='price'> $"+data[i]['price']+"</p>");
//				var h4_type = $("<h4>"+data[i]['classType']+"</h4>");
//				var div_class_detail = $("<div class='d-flex justify-content-between align-items-center mb-3'>")
//
//				div_class_detail.append(h4_type);
//				div_class_detail.append(p_price);
//				
//				var h3_title = $("<h3><a href='viewClass/"+data[i]['cid']+"'>"+data[i]['title']+"</a></h3>");
//				var div_class_content = $("<div class='course-content'>");
//
//				div_class_content.append(div_class_detail);
//				div_class_content.append(h3_title);
//
//				var img_class = $("<img src='"+data[i]['photo']+"' class='img-fluid' alt='no image'>");
//				var div_class_item = $("<div class='course-item'>");
//
//				div_class_item.append(img_class);
//				div_class_item.append(div_class_content);
//
//				var div_class_view = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch'>");
//				
//				div_class_view.append(div_class_item);
//								
//				var div_class_wrap = $("div#class_wrap");	
//						
//				div_class_wrap.append(div_class_view);
//				
//				$('div#loadingCircle').remove();
//			};
//
//		},
//		error: function(xhr, status) {
//			console.log("Error")
//		}
//	})
//});

/*
 這樣也可以註解
*/

function findAllClassByClassType(classtype) {
	$.ajax({
		type: "GET",
		url: "/SpecialTopic/class/api/showClassType/" + classtype,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			$("div#class_wrap").remove();
			var div_class_wrap = $("<div id='class_wrap' class='row'>");
			$('div#row').append(div_class_wrap)
			var count = Object.keys(data).length;
			for (var i = 0; i < count; i++) {


				var p_price = $("<p class='price'> $" + data[i]['price'] + "</p>");
				var h4_type = $("<h4>" + data[i]['classType'] + "</h4>");
				var div_class_detail = $("<div class='d-flex justify-content-between align-items-center mb-3'>")

				div_class_detail.append(h4_type);
				div_class_detail.append(p_price);

				var h3_title = $("<h3><a href='viewClass/" + data[i]['cid'] + "'>" + data[i]['title'] + "</a></h3>");
				var div_class_content = $("<div class='course-content'>");

				div_class_content.append(div_class_detail);
				div_class_content.append(h3_title);

				var img_class = $("<img src='" + data[i]['photo'] + "' class='img-fluid' alt='no image'>");
				var div_class_item = $("<div class='course-item'>");

				div_class_item.append(img_class);
				div_class_item.append(div_class_content);

				var div_class_view = $("<div class='col-lg-4 col-md-6 d-flex align-items-stretch' onclick='location.href=\'SpecialTopic/viewClass\'" + data[i]['cid'] + "'>");

				div_class_view.append(div_class_item);

				var div_class_wrap = $("div#class_wrap");

				div_class_wrap.append(div_class_view);

				$('div#loadingCircle').remove();
			};
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};



// 刪除課程by Cid
function deleteClassByCid(cid) {
	$.ajax({
		type: "Delete",
		url: "/SpecialTopic/class/delete/" + cid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data) {
				$('div#classDiv' + cid).remove();
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};

function changeStatusToOne(cid) {
	$.ajax({
		type: "post",
		url: "/SpecialTopic/class/api/classmanagement/on/" + cid,
		success: function(data) {
			if (data) {
				location.reload()
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};
function changeStatusToZero(cid) {
	$.ajax({
		type: "post",
		url: "/SpecialTopic/class/api/classmanagement/off/" + cid,
		success: function(data) {
			if (data) {
				location.reload()
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};

function changeClassOnlineStatus(cid) {
	$.ajax({
		type: "post",
		url: "/SpecialTopic/class/api/postToOnline/"+cid,
		success: function(data) {
			if (data) {
				$("a#onlinebtn"+cid).attr("class","btn btn-danger")
				$("a#onlinebtn"+cid).html("下架課程")
				console.log(true)
			} else {
				$("a#onlinebtn"+cid).attr("class","btn btn-primary")
				$("a#onlinebtn"+cid).html("上架課程")
				console.log(false)
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};



// 取得課程類別清單
function getClassTypes() {
	$.ajax({
		type: "GET",
		url: "/SpecialTopic/getClassTypeList",
		success: function(data) {
			if (data) {
				//location.reload()
				var typeList = $('ul#typeList');
				var count = Object.keys(data).length;
				var dropdownUL  = $('ul.dropdown-menu');
				for (var i = 0 ; i < count ; i++) {
					typeList.append("<li><a href='/SpecialTopic/class/showClassType/" + data[i] + "'>" + data[i] + "</a></li>");
					if ($('button#dropdownMenuButton1').length > 0 ) {
						dropdownUL.append("<li><a class='dropdown-item' href='/SpecialTopic/class/showClassType/" + data[i] + "'>" + data[i] + "</a></li>");
					}
				}

			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};

$(document).ready(function() {
	getClassTypes()
})