function sc_del(cid) {
	$('button#sc_btn_' + cid).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
	$.ajax({
		type: "delete",
		url: "/SpecialTopic/ShoppingCart/" + cid,
		success: function(data) {
			if (data == "success") {
				if ($('a#sum').length > 0) {
					$('a#sum').html(parseInt($('a#sum').html()) - parseInt($('h4#price' + cid).html()))
				}
				$('a#total').html(parseInt($('a#total').html()) - 1)

				if (typeof ($('div#item1')[0]) != 'undefined') {
					$('div#item' + cid).remove();
				} else {
					$('button#sc_btn_' + cid).attr('onclick', "sc_add(" + cid + ")");
					$('button#sc_btn_' + cid).addClass("btn-success");
					$('button#sc_btn_' + cid).removeClass("btn-danger");
					$('button#sc_btn_' + cid).html("加入至購物車");
				}
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
	$('button#sc_btn_' + cid).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
	$.ajax({
		type: "post",
		url: "http://localhost:8080/SpecialTopic/ShoppingCart/" + cid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data != "") {
				if ($('span#shoppingcart_count').html() == "") { $('span#shoppingcart_count').html(0) };
				$('span#shoppingcart_count').html(1 + parseInt($('span#shoppingcart_count').html()));
				$('button#sc_btn_' + cid).removeClass("btn-success");
				$('button#sc_btn_' + cid).addClass("btn-danger");
				$('button#sc_btn_' + cid).attr('onclick', "sc_del(" + cid + ")");
				$('button#sc_btn_' + cid).html("從購物車中移出");
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

	$('form#idFormAioCheckOut').submit();
}

function getShoppingCartList() {
	$.ajax({
		type: "get",
		url: "/SpecialTopic/ShoppingCart/api/getList",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			indexRender(data)
		},
		error: function(xhr, status) {
			console.log("Error")
		}
	})
}

function indexRender(data) {
	if (data != null) {
		var count = Object.keys(data).length;

		var a1 = $("<a class='position-relative' href='/SpecialTopic/ShoppingCart/getInfo'></a>");
		var i1 = $('<i class="fa fa-shopping-cart" style="font-size: 24px; color: #5fcf80"></i>');
		var s2 = $('<span id="shoppingcart_count" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"></span>');

		if (count != 0) {
			s2.append(count);
		}

		a1.append(i1);
		a1.append(s2);

		$('li#scl').append(a1);

	}
}

getShoppingCartList();	