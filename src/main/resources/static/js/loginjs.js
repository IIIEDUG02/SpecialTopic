var xmlhttp = new XMLHttpRequest();
var url = "/SpecialTopic/getLoginStatus";

xmlhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		var data = JSON.parse(this.responseText);
		indexRender(data);
	}
};

xmlhttp.open("GET", url, true);
xmlhttp.send();

function indexRender(data) {
	if (data == null) {
		$("li#dp").remove();
	} else if (data.username != "null") {
		$("a#login").remove();
		$("div#header2").prepend("<a href='logout_page' class='get-started-btn'>登出</a>");
		$("a#register").remove();
	} else {
		$("li#dp").remove();
	}
}
