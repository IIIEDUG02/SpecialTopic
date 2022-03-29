function sc_del(sid) {
	$.ajax({
		type: "delete",
		url: "/SpecialTopic/ShoppingCart/" + sid,
		success: function(data) {
			if (data == "success") {
				$('a#sum').html(parseInt($('a#sum').html()) - parseInt($('h4#price' + sid).html()))
				$('a#total').html(parseInt($('a#total').html()) - 1)
				$('div#item' + sid).remove();
				$('span#shoppingcart_count').html(parseInt($('span#shoppingcart_count').html()) - 1);
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
}

function sc_add(cid) {
	$.ajax({
		type: "post",
		url: "http://localhost:8080/SpecialTopic/ShoppingCart/" + cid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data != "") {
				//var del_btn = $('<div class="col-2"><button type="button" class="btn btn-danger"	onclick="sc_del(' + data['id'] + ')">刪除</button></div>');
				//var h4_price = $('<div class="col-4"><h4 id="price$' + data['classBean']['price'] + '">${item.getClassBean().getPrice()}</h4></div>')
				//var h4_title = $('<div class="col-6"><h4>' + data['classBean']['title'] + '</h4></div>')
				//var div = $('<div id="' + data['id'] + '}" class="row m-3 shadow p-1"></div>')
				//div.append(h4_title,h4_price,del_btn);
				//console.log(data)
				$('span#shoppingcart_count').html(parseInt($('span#shoppingcart_count').html()) + 1);
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
}

function checkout() {
	$('input#TotalAmount').val($('a#sum').html());
	$('input#TradeDesc').val("TEST");
	$('input#ItemName').val("");
	$('h4#class_title').each(function() {
		if ($('input#ItemName').val() != "") {
			$('input#ItemName').val($('input#ItemName').val() + "#")
		}
		$('input#ItemName').val($('input#ItemName').val() + $(this).html());
		
	});
	
	$('input#hidden_cid').each(function() {
		if ($('input#CidList').val() != "") {
			$('input#CidList').val($('input#CidList').val() + "#")
		}
		$('input#CidList').val($('input#CidList').val() + $(this).val());
	});
	
	
	$.confirm({
		title: '確認付款',
		content: '請檢查付款資訊是否正確，確認後將前往綠界支付頁面。',
		buttons: {
			confirm:  {
				text: '確認',
				btnClass: 'btn-success',
				action : function() {
					$('form#idFormAioCheckOut').submit();
				}
			},
			cancel: {
				text: '取消',
				btnClass: 'btn-danger',
				action : function() {
					$.alert('取消付款!');
				}
			},
		}
	});
}
