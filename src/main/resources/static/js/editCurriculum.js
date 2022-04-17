function editCurriculum(cuid) {
	var oriForm = $('div form');
	if (oriForm.length != 0) {
		oriForm.remove();
	}
	var formDiv = $('div#formDiv');
	var form1 = $("<form action='/SpecialTopic/createcurriculum' method='post' enctype='multipart/form-data' ></form>")
	var chapterDiv = $("<div id='chapterDiv' >章節名稱：<input name='chapter' type='text' /></div>");
	var cuidInput = $("<input value='" + cuid +"' type='hidden' name='cuid'/>");
	var videoPathDiv = $("<div id='videoPathDiv'>影片網址：<input name='myVideo' type='file'></input></div>");
	var submitBtn = $("<input class='mt-2' id='submitBtn' type='submit' value='送出' />");
	var editBtn = $("<input id='editBtn' type='hidden' onclick='editBtnOnclick()' value='編輯' />");
	var delBtn = $("<input id='delBtn' type='hidden' onclick='deleteBtnOnclick("+cuid+")' value='刪除' />");
	
	if (cuid != 0) {
		cuidInput.val($("input#cuid" + cuid).val());
		chapterDiv.html("章節名稱：<a>" + $("input#chapter" + cuid).val() + "</a><input name='chapter' type='hidden' value='"+ $("input#chapter" + cuid).val() + "' >");
		videoPathDiv.html("影片：<video controls src='" + $("input#videoPath" + cuid).val() + "'></video><input name='myVideo' type='hidden' value='" + $("input#videoPath" + cuid).val() + "'></input>");
		submitBtn.attr("type","hidden");
		editBtn.attr("type","button");
		delBtn.attr("type","button");
	} else {
		cuidInput.val(0);
	}
	
	form1.append(cuidInput);
	form1.append(chapterDiv);
	form1.append(videoPathDiv);
	form1.append(submitBtn);
	form1.append(editBtn);
	form1.append(delBtn);
	
	
	formDiv.append(form1);
	
}

function editBtnOnclick() {
	$("input#delBtn").attr("type","hidden");
	$("input#editBtn").attr("type","hidden");
	$("input#submitBtn").attr("type","submit");
	$("form").attr("action","/SpecialTopic/updatecurriculum");
	$("form").attr("method","post");
	
	$("div#chapterDiv a").html("");
	$("div#chapterDiv input").attr("type","text");
	$("div#videoPathDiv video").remove();
	$("div#videoPathDiv input").attr("type","file");
		
}
function deleteBtnOnclick(cuid){
		$.ajax({
		type: "Delete",
		url: "/SpecialTopic/class/delete/curriculum/" + cuid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data) {
				$('li#li' + cuid).remove();
				$('div form').remove();
				location.reload;
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
};


	


