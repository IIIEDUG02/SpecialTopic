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
		$("a#register").remove();
	} else {
		$("li#dp").remove();
	}
}
