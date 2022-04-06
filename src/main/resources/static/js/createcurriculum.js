	
	function creatForm() {		
		var form_cu = $("<form data-purpose='section-form' class='item-form--item-form--2egBT'>");
		var div_df = $("<div class='df db-sm'>")
		var div_new_chapter = $("<div class='mt5 mr5 mb5 semibold'>新章節：</div>")
		var div_fx = $("<div class='fx'>")
		var div_form_group_title = $("<div data-purpose='section-form-group-title' class='form-group form-group-sm'>")
		var div_form_control_container = $("<div class='form-control-counter-container'>")
		var input_title = $("<input maxlength='80' placeholder='輸入標題' data-purpose='section-title' type='text' id='title' class='form-control' value=''>")
		var div_test_right = $("<div class='text-right'>")
		var btn_cancel = $("<button data-purpose='cancel-section-form' type='button' class='btn btn-sm btn-tertiary'>")
		var span_cancel = $("<span class='ellipsis' style='max-width: 85px;'>取消</span>")
		var btn_submit = $("<button type='submit' data-purpose='submit-section-form' class='ml5 btn btn-sm btn-secondary'>")
		var span_submit = $("<span class='ellipsis' style='max-width: 145px;'>加入章節</span>")
		var div_createform = $("div#createform")
		form_cu.append(div_df);
		div_df.append(div_new_chapter);
		div_df.append(div_fx);
		div_fx.append(div_form_group_title);
		div_form_group_title.append(div_form_control_container);
		div_form_control_container.append(input_title);
		div_fx.append(div_test_right);
		div_test_right.append(btn_cancel);
		btn_cancel.append(span_cancel);
		div_test_right.append(btn_submit);
		btn_submit.append(span_submit);
		div_createform.append(form_cu);
};

$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/SpecialTopic/getCurListJson/api/"+cid,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			
			
			
			},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
});
