function getShoppingCartList() {
	$.ajax({
		type: "get",
		url: "/SpecialTopic/ShoppingCart/getList",
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
		if (count != 0) {
			var a1 = $("<a class='position-relative' href='/SpecialTopic/ShoppingCart/getInfo'></a>");
			var i1 = $('<i class="fa fa-shopping-cart" style="font-size: 24px; color: #5fcf80"></i>');
			var s1 = $('<span class="visually-hidden">unread messages</span>');
			var s2 = $('<span id="shoppingcart_count" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"></span>');

			s2.append(count);
			s2.append(s1);

			a1.append(i1);
			a1.append(s2);

			$('li#scl').append(a1);

		}
	}
}

getShoppingCartList();	