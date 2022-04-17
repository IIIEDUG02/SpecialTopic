function mamermange() {

	$.ajax({
		type: "POST",
		url: "/SpecialTopic/member/membermanage/"+ $("#search").val(),
//		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data == null) {
				console.log("no data return");
			} else {
				$("tbody tr").each(function() {
					$(this).remove();
				}
				)
			var tr = $("<tr> </tr>");
			var id = $("<th>1</th>");
			var name = $("<td>"+data["username"]+"</td>");
			var act = $("<td>"+data["activated"]+"</td>");
			var iputtd = $("<td> </td>");
			var iput = $('<input type="button" name="input1" class="btn btn-success" value="編輯">');
			var iput1 = $('<input type="button" style="margin-left: 30px" class="btn btn-danger" value="刪除">');
									
			iputtd.append(iput);
			iputtd.append(iput1);
			tr.append(id);
			tr.append(name);
			tr.append(act);
			tr.append(iputtd);
			
			$("tbody#tbody1").append(tr);
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	}
	)
};