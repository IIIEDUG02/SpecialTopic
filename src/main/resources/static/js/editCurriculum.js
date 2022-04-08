function editCurriculum(cuid) {
	var oriForm = $('div form');
	if (oriForm.length != 0) {
		oriForm.remove();
	}
	var formDiv = $('div#formDiv');
	var form1 = $("<form action='' method='' ></form>")
	var chapterDiv = $("<div id='chapterDiv'>章節名稱：<input type='text' /></div>");
	var cuidInput = $("<input value='" + cuid +"' type='hidden' name='cuid'/>");
	var videoPathDiv = $("<div id='videoPathDiv'>影片網址：<input name='' type='file'></input></div>");
	var submitBtn = $("<input id='submitBtn' type='button' value='送出' />");
	var editBtn = $("<input id='editBtn' type='hidden' onclick='editBtnOnclick()' value='編輯' />");
	var delBtn = $("<input id='delBtn' type='hidden' onclick='' value='刪除' />");
	
	if (cuid != 0) {
		cuidInput.val($("input#cuid" + cuid).val());
		chapterDiv.html("章節名稱：<a>" + $("input#chapter" + cuid).val() + "</a><input name='chapter' type='hidden' value='"+ $("input#chapter" + cuid).val() + "' >");
		videoPathDiv.html("影片：<video controls src='" + $("input#videoPath" + cuid).val() + "'></video><input name='video_path' type='hidden' value='" + $("input#videoPath" + cuid).val() + "'></input>");
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
	$("input#submitBtn").attr("type","button");
	$("form").attr("action","");
	$("form").attr("method","put");
	
	$("div#chapterDiv a").html("");
	$("div#chapterDiv input").attr("type","text");
	$("div#videoPathDiv video").remove();
	$("div#videoPathDiv input").attr("type","file");
	
	
	
}