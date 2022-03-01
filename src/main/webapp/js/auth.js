/**
 * 用來登入驗證使用 By 1+
 */


/**
let headers = {
	"Content-Type": "application/json",
	"Accept": "application/json",
	"iiideug02": "LoginAuth"
};

const formData = new FormData(document.getElementById('loginForm'));
formData.get('username');
formData.get('password');

let promise = fetch("http://localhost:8080/SpecialTopic/LoginAuthApi", {
	method: "POST",
	headers: headers,
	body: formData
}).then(function(response) {
	console.log(headers);
	return response.json();
})
	.then(function(myJson) {
		console.log(myJson);
});
 */
/*
var xhr = new XMLHttpRequest();
var data = "username=" + encodeURIComponent($('#usernametext').val())
        + "&password=" + encodeURIComponent($('#passwordtext').val());
xhr.open("POST", "http://localhost:8080/SpecialTopic/LoginAuthApi", true);
xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("iiideug02", "LoginAuth");
xhr.addEventListener("load", transferComplete);

xhr.send(data);
 
function transferComplete(evt) {
  console.log(evt);
  var jsonResponse = JSON.parse(evt.target.responseText);
  console.log(jsonResponse['result']);
  if (jsonResponse['result'] == "Success Loggin") {
	window.location.replace("/SpecialTopic/MemberList.jsp")
  }else {
	alert('Failed Loggin');
  }
};
 */
 
 /**
 * 用來登入驗證使用 By 1+
 */


/**
let headers = {
	"Content-Type": "application/json",
	"Accept": "application/json",
	"iiideug02": "LoginAuth"
};

const formData = new FormData(document.getElementById('loginForm'));
formData.get('username');
formData.get('password');

let promise = fetch("http://localhost:8080/SpecialTopic/LoginAuthApi", {
	method: "POST",
	headers: headers,
	body: formData
}).then(function(response) {
	console.log(headers);
	return response.json();
})
	.then(function(myJson) {
		console.log(myJson);
});
 */
$('#loginForm').submit(function() {
	var xhr = new XMLHttpRequest();
	var data = "username=" + encodeURIComponent($('#usernametext').val())
		+ "&password=" + encodeURIComponent($('#passwordtext').val());
	xhr.open("POST", "http://localhost:8080/SpecialTopic/LoginAuthApi", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("iiideug02", "LoginAuth");
	xhr.addEventListener("load", function(evt) {
		console.log(evt);
		var jsonResponse = JSON.parse(evt.target.responseText);
		console.log(jsonResponse['result']);
		if (jsonResponse['result'] == "Success Loggin") {
			window.location.replace("/SpecialTopic/MemberList.jsp")
		} else {
			alert('Failed Loggin');
		}
	});
	xhr.send(data);
});


