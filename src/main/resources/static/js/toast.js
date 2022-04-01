/**
 * Toast 為 Bootstrap 提供的元件，能夠顯示提示方塊。
 */
 
const toast = document.querySelector('#toast')
const body = document.querySelector('#toast-body')

function showToast(message) {
	body.innerText = message
	new bootstrap.Toast(toast).show()
}
