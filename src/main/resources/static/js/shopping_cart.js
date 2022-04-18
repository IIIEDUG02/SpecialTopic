var CHECKOUTUTL = "/SpecialTopic/ShoppingCart/getInfo";

function sc_del(cid) {
	$('button#sc_btn_' + cid).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
	$.ajax({
		type: "delete",
		url: "/SpecialTopic/ShoppingCart/api/" + cid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data != null) {
				if ($('a#sum').length > 0) {
					$('a#sum').html(parseInt($('a#sum').html()) - parseInt($('h3#price' + cid).html()))
				}
				$('a#total').html(parseInt($('a#total').html()) - 1)
				if ($('a#total').html() == "0") {
					$('a#total').html("");
					$('a.checkout').attr("href", "")
				}
				$('li.scitem' + cid).remove();

				if (typeof ($('div#item1')[0]) != 'undefined') {
					$('div#item' + cid).remove();
				} else {
					$('button#sc_btn_' + cid).attr('onclick', "sc_add(" + cid + ")");
					$('button#sc_btn_' + cid).addClass("btn-success");
					$('button#sc_btn_' + cid).removeClass("btn-danger");
					$('button#sc_btn_' + cid).html("加入購物車");
				}
				$('span#shoppingcart_count').html(parseInt($('span#shoppingcart_count').html()) - 1);
				if (parseInt($('span#shoppingcart_count').html()) == 0) {
					$('div.shopping-cart').attr("style","display: none")
					$('span#shoppingcart_count').attr("style","display: none")
				}
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log(xhr);
			console.log(status);
		}
	})
}

function sc_add(cid) {
	$('button#sc_btn_' + cid).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>');
	$.ajax({
		type: "post",
		url: "/SpecialTopic/ShoppingCart/api/" + cid,
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			if (data != "") {
				if ($('span#shoppingcart_count').html() == "") { $('span#shoppingcart_count').html(0) };
				$('span#shoppingcart_count').html(1 + parseInt($('span#shoppingcart_count').html()));
				$('button#sc_btn_' + cid).removeClass("btn-success");
				$('button#sc_btn_' + cid).addClass("btn-danger");
				$('button#sc_btn_' + cid).attr('onclick', "sc_del(" + cid + ")");
				$('button#sc_btn_' + cid).html('移除購物車');
				$('a.checkout').attr("href", CHECKOUTUTL);
				$('span#shoppingcart_count').attr("style","display: inline-block")
				var ul = $('ul.shopping-cart-items');
				var li = addItem(data);
				ul.append(li);
			} else {
				alert("網頁發生錯誤");
			}
		},
		error: function(xhr, status) {
			console.log(xhr);
			console.log(status);
		}
	})
}

function checkout() {
	$('input#TotalAmount').val($('a#sum').html());
	$('input#TradeDesc').val("TEST");
	$('input#ItemName').val("");
	$('h3#class_title a').each(function() {
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
			listRender(data)
		},
		error: function(data) {
			console.log(data)
		}
	})
}

function listRender(data) {
		var count = Object.keys(data).length;
		// badge build
		var a1 = $("<a id='cart' class='position-relative' href='#'></a>");
		var icon = $('<i class="fa fa-shopping-cart" style="font-size: 24px; color: #5fcf80"></i>');
		var badge = $('<span id="shoppingcart_count" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"></span>');

		if (count != 0) {
			badge.append(count);
		}

		a1.append(icon);
		a1.append(badge);

		$('li#scl').append(a1);

		// 點擊出現框框
		a1.on("click", function() {
			if ($('span#shoppingcart_count').html() > 0 ) {
				$(".shopping-cart").fadeToggle("fast");
			}
		});

		// item list build
		var ul = $('ul.shopping-cart-items');
		for (var i = 0; i < count; i++) {
			var classBean = data[i]["classBean"];
			var li = addItem(classBean);
			ul.append(li);
			
		}
}

function addItem(classBean) {
	var li = $('<li class="row scitem' + classBean["cid"] + '"></li>');
	
	var divItem = $('<div class="col-9"></div>');
	var divName = $('<div class="item-name"></div>');
	divName.append(classBean["title"]);
	var divPrice = $('<div class="item-price"></div>');
	var aPrice = $("<a></a>")
	aPrice.append(classBean["price"]);
	divPrice.append("$");
	divPrice.append(aPrice);
	divItem.append(divName);
	divItem.append(divPrice);
	
	var divI = $('<div class="col-3"></div>')
	var iR = Trash = $('<a href="javascript:sc_del(' + classBean["cid"] + ')"><i class="fa fa-trash fa-2x"></i></a>')
	divI.append(iR);

	li.append(divItem);
	li.append(divI);
	li.append("<hr / >");
	return li;
}