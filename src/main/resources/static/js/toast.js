/**
 * 
 */
 
const toast = document.querySelector('#toast')
const body = document.querySelector('#toast-body')

function showToast(message) {
	body.innerText = message
	new bootstrap.Toast(toast).show()
}
