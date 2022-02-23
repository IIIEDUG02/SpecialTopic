/**
 * 用來登入驗證使用 By 1+
 */

var data = {
	"username": "nilm987521",
	"password": "abcd1234"
}
	;

let headers = {
	"Content-Type": "application/json",
	"Accept": "application/json",
	"iiideug02": "LoginAuth"
};

let promise = fetch("http://localhost:8080/SpecialTopic/LoginAuthApi", {
	method: "POST",
	headers: headers,
	body: data
}).then(function(response) {
	return response.json();
})
	.then(function(myJson) {
		console.log(myJson);
	});