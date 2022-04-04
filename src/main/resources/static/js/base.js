/**
 Author: Anna
 
 JS 的通用類別，主要提供通用元件與功能
 如: Loading...(spinner) 元件、Toast(Bootstrap 提供) 元件與 AJAX 請求。
 其他 JS 可繼承此類別再擴充自己需要的功能。
*/

class Base {
	constructor() {
		// Loading... 的 html template
		this.spinner = `
  	    	<div class="modal-backdrop fade show"></div>
  	    	<div class="spinner-border text-secondary" role="status"
  	    			 style="  color: #5fcf80 !important; position: fixed; left: 45%; top: 45%; width: 5rem;
  										  height: 5rem; z-index: 9999;"
  	    	>
  	    		<span class="visually-hidden">Loading...</span>
	    		</div>`
	  this.toast = document.querySelector('#toast')
		this.toastBody = document.querySelector('#toast-body')
	}

	// 請求超時中斷函數
	static timeout(s) {
		return new Promise(function(_, reject) {
			setTimeout(function() {
				reject(new Error(`請求時間過長(${s} 秒)。`));
			}, s * 1000);
		});
	}

	// 使用原生(native)的方式(JS)來發 AJAX 請求,async JS 的非同步關鍵字
	// csrf = Cross Site Request Forgery 跨站請求偽造(防止請求偽造)
	static async ajax(timeout, formData = undefined, csrf = undefined) {
		try {
			// fetch 是一個 JS 的函數，能夠用來發送 AJAX 請求
			const fetchPro = fetch(ArticlesPage.DELETE_URL, {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
					// 專案目前取消 CSRF，因此先註解
					//之後上伺服器後，下面那句要解開，向後端傳送一個確認是否偽造請求的驗證碼
					// 'X-CSRF-TOKEN': csrf,
				},
				// body 用來放要往後端傳送的資料
				body: JSON.stringify(formData),
			});

			// 等待後端回傳結果
			const res = await Promise.race([
				fetchPro,
				timeout(ArticlesPage.TIMEOUT_SEC),
			]);
			const data = await res.json();

			if (!res.ok) throw new Error(`${data.message} (${res.status})`);
			return data;
		} catch (error) {
			throw error;
		}
	}
	
	// 顯示 Toast
	showToast(message) {
		this.toastBody.innerText = message
		new bootstrap.Toast(this.toast).show()
	}

	// 將 Loading... 元素添加至 body 上面
	showSpinner() {
		this.body.insertAdjacentHTML('beforeend', this.spinner)
	}

	// 從 body 中移除 Loading... 元素
	removeSpinner() {
	  const modal = document.querySelector('.modal-backdrop')
	  const spinner = document.querySelector('.spinner-border')
	  
	  this.body.removeChild(modal)
	  this.body.removeChild(spinner)
	}
}
