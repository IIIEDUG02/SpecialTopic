function mamermange() {

	$.ajax({
		type: "POST",
		url: "/SpecialTopic/member/membermanage/"+ $("#search").val(),
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
			var name = $("<td id='username_td'>"+data["username"]+"</td>");
			
			var iputtd = $("<td> </td>");
			
			if(data["activated"] == 1){
				var iput = $('<input type="button" id="input1" onclick="deactivated()" class="btn btn-danger" value="停用">');				
				var act = $("<td id='act_col'>啟用</td>");
			}else{
				var iput = $('<input type="button" id="input1" onclick="activated()" class="btn btn-danger" value="啟用">');
				var act = $("<td id='act_col'>停用</td>");
			}
									
			iputtd.append(iput);
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

function activated(){
	$.ajax({
		type: "POST",
		url: "/SpecialTopic/member/activated/"+ $("td#username_td").html(),
		contentType: "application/json",
		success: function(data){
			if(data == 1){
				$("td#act_col").html("啟用");
				$("input#input1").val("停用");
				$("input#input1").attr("onclick","deactivated()");
			}
		}
	})
}

function deactivated(){
	$.ajax({
		type: "POST",
		url: "/SpecialTopic/member/deactivated/"+ $("td#username_td").html(),
		contentType: "application/json",
		success: function(data){
			if(data == 0){
				$("td#act_col").html("停用");
				$("input#input1").val("啟用");
				$("input#input1").attr("onclick","activated()");
			}
		}
	})
}




