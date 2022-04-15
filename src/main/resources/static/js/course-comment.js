/**
 * Author: Anna
 * 
 * 課程頁面留言都是以動態的方式來操作，因此會有很多 Template String，看起來會較凌亂。
 * 若之後有學習到前端框架，可將這些寫成 Component。
 * 
 */
class CourseComment extends Base {
	static COMMENTS_URL = `/SpecialTopic/api/comment/comments?cbt={0}&cid={1}&type=course&limit={2}`
	static CREATE_URL = `/SpecialTopic/api/comment/create/{0}`;
	static UPDATE_URL = `/SpecialTopic/api/comment/{0}`;
	static LIKE_URL = `/SpecialTopic/api/comment/{0}/{1}`;
	static CREATED_MSG = "您的留言已新增成功！";
	
  constructor() {
    super();
    
    // 回覆留言框的模板
    this.replyCommentTemplate = `
    <div class="sub-comment reply-comment animate__animated animate__fadeIn">
      <div>
        <div class="sub-comment__img profile-image profile-image-xs">
          <a class="" href="javascript:void(0);">
            <img src="/SpecialTopic/img/default_avatar.png" width="" class="sc-cx4oas-0 aratar--img loaded">
          </a>
        </div>
    
        <div class="comment-wrap">
          <div class="comment-extra-info text-sm">
            <div class="first-row">
              <a href="javascript:void(0);" class="reply-username username"></a>
            </div>
          </div>
    
          <div class="comment-content-edit" style="height: auto;">
            <textarea id="commentTextarea" placeholder="留言" class="pad-t-10 pad-r-10 pad-b-10 pad-l-10" value=""></textarea>
          </div>
    
          <div class="text-right" style="opacity: 1;">
            <div class="comment-description">
              <div class="sc-1xf9k5m-1 button-wrapper">
              <button class="button--cancel reply-btn-cancel btn-hollow btn-sm marg-r-10">取消</button>
                <button class="button--submit" disabled>留言</button>
              </div>
    
              <div class="comment-description__text">
                - 在這裡確認你的學習需求吧
                <br>
                - 盡情和老師與同學交流討論
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    `;

    // 回覆 button 的模板
    this.replyButtonTemp = `
    <span class="reply-btn reply-text pseudo-btn"><i class="bi bi-reply-fill pad-r-5"></i>回覆</span>
    `;

    // 因為是區塊 spinner，因此重寫一個
    this.spinner = `
    <div id="commentMask" class="mask"></div>
    <div id="commentSpinner" class="spinner-border text-secondary" role="status"
          style="color: #5fcf80 !important; position: absolute; left: calc(50% - 5rem / 2); 
          top: calc(50% - 5rem / 2); width: 5rem; height: 5rem; z-index: 9999;"
    >
      <span class="visually-hidden">Loading...</span>
    </div>
    `;

    // 會用到的所有 elements 名稱都統一放在這裡，使用 document.querySelector 時會用到
    this.elements = {
      commentsWrapper: ".comments-wrapper",
      commentsBody: ".comments",
      firstItem: ".first-level-isolated-comment-item",
      firstItemUsernameEl: "#firstItemUsername",
      firstItemSubmitBtn: ".first-item-submit-btn",
      replyBtn: ".reply-btn",
      commentEl: ".main-comment",
      replyCommentEl: ".reply-comment",
      replyUsernameEl: ".reply-username",
      replySubmitBtn: ".button--submit",
      replyCancelBtn: ".reply-btn-cancel",
      observer: ".observer",
      mask: "#commentMask",
      spinner: "#commentSpinner",
      loadMoreEl: ".fake-comment-block",
      loadMoreBtn: ".fake-item__button",
      editBtn: ".btn-edit",
      editedTimeEl: ".edited-time",
      heartBtn: ".heart-container",
      heartIcon: ".heart-icon",
    };

    this.commentsWrapper = document.querySelector(this.elements.commentsWrapper);
    this.username = "";
    
    // Created Before Timestamp，某個時間點，由後端回傳，詳見 CommentController
    this.cbt = parseInt(new Date().getTime() / 1000);
    
    // 向後端要資料時的指定筆數
    this.limit = 3;
    
    this.init();
  }
  
  get commentsBody() {
		return document.querySelector(this.elements.commentsBody);
	}
  // getter 方法，將網址列的課程代號(cid)取出
  get cid() {
		const cid = location.href.split("/")[location.href.split("/").length - 1];
		
		return isNaN(Number(cid)) ? 0 : Number(cid);
	}
	
	// getter 方法，將 loadMoreEl 動態取出
	get loadMoreEl() {
		return document.querySelector(this.elements.loadMoreEl);
	}
	
	// getter 方法，將 COMMENTS_URL 字串格式化之後回傳
	get commentsUrl() {
		return CourseComment.COMMENTS_URL.format(this.cbt, this.cid, this.limit);
	}
	
	// getter 方法，取得第一個固定元素，也就是第一個留言區塊
	get firstItem() {
		return document.querySelector(this.elements.firstItem);
	}
	
  // 初始化 method，類別實例化之後會需要先執行的東西放這
  init() {
    // 因使用到 moment.js 要做本地化
    moment.lang("zh-tw");
    
    this.commentsBody.addEventListener('click', this.commentsBodyClickHandler.bind(null, this));
    this.initFirstItem();
    this.initIntersectionObserverListener();
  }
	
	// 自訂 document.querySelector 的一個簡便寫法
	getEl(name, el = undefined) {
		if(el)
			return el.querySelector(this.elements[name]);
			
		return document.querySelector(this.elements[name]);	
	}
	
	// 判斷某個元素是否有某個 class 名稱
	hasClass(obj, target, className) {
		return target.classList.contains(obj.elements[className].slice(1));
	}
	
	// 將 click event 註冊在最外層的 container，之後再用事件委派的方式去呼叫相對應的 event handler
	commentsBodyClickHandler(obj, e) {
		const t = e.target;
		const tagName = t.tagName.toLowerCase();

		if (tagName === "span" && obj.hasClass(obj, t, "replyBtn")) {
			obj.addReplyCommentElement(obj, t);
		}
		else if ( obj.username && (tagName === "div" && obj.hasClass(obj, t, "heartBtn") || 
		tagName === "span" && obj.hasClass(obj, t, "heartIcon")) ) {
			obj.heartBtnClickHandler(obj, t, tagName);
		}
		else if (tagName === "span" && obj.hasClass(obj, t, "editBtn")) {
			obj.editBtnClickHandler(obj, t);
		}
		else if (tagName === "button" && obj.hasClass(obj, t, "replyCancelBtn")) {
    	const div = t.closest(obj.elements.commentEl).lastElementChild;
			obj.removeReplyCommentElement(obj, div);
		}
		else if (tagName === "button" && obj.hasClass(obj, t, "firstItemSubmitBtn")) {
			obj.firstItemSubmitBtnClickHandler(obj, e);
		}
		else if (tagName === "button" && obj.hasClass(obj, t, "replySubmitBtn")) {
			obj.replySubmitBtnClickHandler(obj, e);
		}
		else if (tagName === "button" && obj.hasClass(obj, t, "loadMoreBtn")) {
			obj.loadMoreBtnClickHandler(obj);
		}
	}
	
	// 初始化第一個 Item，也就是留言版區塊的最上面的一個固定元素(就是想要新增留言的那個區塊)
  initFirstItem() {
    if (this.firstItem) {
    	this.firstItem
        .querySelector("textarea")
        .addEventListener("keyup", this.textareaKeyUpHandler.bind(null, this));
    }
  }

  // 用來監聽滾輪滑動，到一定位置時才將留言加載到畫面上
  initIntersectionObserverListener() {
    const target = this.getEl("observer");
    const observer = new IntersectionObserver(this.observerHandler.bind(null, this), {
      root: null,
      threshold: 1,
    });

    observer.observe(target);
  }
  
  // 動態設定使用者的名字
  setFirstItemUsername(username) {	
    if (username) {
			const usernameEl = this.getEl("firstItemUsernameEl", this.firstItem);
			
		if (usernameEl)
			this.username = usernameEl.innerText = username;
		}
	}
	
	// 設定回覆框上面的名字
	setReplyItemUsername(el) {
		const replyUsernameEl = this.getEl("replyUsernameEl", el);
    const usernameEl = this.getEl("firstItemUsernameEl");
    
    replyUsernameEl.innerText = usernameEl.innerText;
	}
	
	// 顯示載入中的元件
  showSpinner(element) {
		if (element) {
			element.insertAdjacentHTML("beforeend", this.spinner);
			this.getEl("mask").style.background = "#fff";
		} else {
			this.commentsWrapper.insertAdjacentHTML("beforeend", this.spinner);
		}
  }
  
	// 移除載入中的元件
  removeSpinner() {
    const mask = this.getEl("mask");
    const spinner = this.getEl("spinner");

    mask.remove();
    spinner.remove();
  }
	
  // 回覆 button 的 handler，點擊按鈕後將回覆框動態增加到頁面上
  addReplyCommentElement(obj, el) {
    // 往外層取得指定的 parent element
    const comment = el.closest(obj.elements.commentEl);

    // 之後取得最後一個元素，也就是包住回覆 button 的 div
    const div = comment.lastElementChild;

    obj.getEl("replyBtn", div).remove();

    // 將回覆框動態添加到頁面上
    div.insertAdjacentHTML("beforeend", obj.replyCommentTemplate);
    
    // 之後為回覆框上面的元素添加事件
    const replyComment = obj.getEl("replyCommentEl", comment);
    const textarea = replyComment.querySelector("textarea");
    
    // 設定使用者名字
    obj.setReplyItemUsername(replyComment);
    
    // 監聽 textarea 輸入，若為空則不能按送出
    textarea.addEventListener(
      "keyup",
      obj.textareaKeyUpHandler.bind(null, obj)
    );
  }
	
  // 從頁面上移除回覆框元件 (按"取消"留言的意思)
  removeReplyCommentElement(obj, div) {
    obj.getEl("replyCommentEl", div).remove();
    div.insertAdjacentHTML("beforeend", obj.replyButtonTemp);
  }
  
  // 愛心 button click handler
  async heartBtnClickHandler(obj, t, tagName) {
		// 因愛心本身是 span，父元素是 div，因次想要兩個 click 都觸發進來後
		// 都需要再重新定位回父元素 div 上面
		//按讚收回讚的愛心是搭配哪一則留言(找父留言)
		const container = tagName === "div" ? t : t.parentElement;
		let heart = tagName === "div" ? t.querySelector("span") : t;
		let url;
		const l = heart.classList;
		const comment = heart.closest(".sub-comment") || heart.closest(".main-comment");
		
		//切換按讚收回讚的實心空心愛心
		//空心愛心
		l.toggle("fa-heart-o");
		l.toggle("heart");
		
		//實心愛心
		l.toggle("fa-heart");
		l.toggle("heart--filled");
		
		// 如果點了讚，否則就是收回讚
		if (l.contains("heart--filled")) {
			container.lastChild.textContent = parseInt(container.lastChild.textContent) + 1;
			url = CourseComment.LIKE_URL.format(comment.id, "like");
		} else {
			container.lastChild.textContent = parseInt(container.lastChild.textContent) - 1;
			url = CourseComment.LIKE_URL.format(comment.id, "unlike");
		}
		
		const data = await obj.resultHandler("POST", url, undefined,  obj, undefined, false);
			
		if (data)
			console.log(data);
	}
	
  // 編輯留言 button click handler
  //obj物件，t=target 目標物件(修改的那個按鈕本身,按它的時候會被傳進來)
  editBtnClickHandler(obj, t) {
		// 上一層的父元素 //找最近的父親div
		const extraInfoEl = t.parentElement;
		
		// 下一個同級的元素 //修改這個動作會找它搭配的留言
		let nextEl = extraInfoEl.nextElementSibling;
		const content = nextEl.innerText;
		
		t.remove();
		nextEl.remove();
		extraInfoEl.insertAdjacentHTML('afterend', obj.generateEditCommentMarkup(content));
		nextEl = extraInfoEl.nextElementSibling;
		
		// 將 "this" 指向 nextEl
		// 將 button 的事件委派給他們的父親元素
		// 點擊"修改"這個動作會傳給父親div，父親div會去做觸發，打開下面要修改的留言欄這個動作。
		nextEl.addEventListener("click", obj.editCommentClickHandler.bind(nextEl, obj, extraInfoEl, content));
		
		//textarea 指被打開的留言欄
		const textarea = nextEl.querySelector("textarea");
		const btn = obj.getEl("replySubmitBtn", nextEl);
		
		//監聽你有沒有做修改內容的動作，沒修改不能按修改
		textarea.addEventListener("keyup", function() {
		content === textarea.value.trim() ? btn.disabled = true : btn.disabled = false;
		});
	}
	
	// 移除 "修改留言框" (沒修改內容，按"取消"的這個動作)
	removeEditComment(nextEl, obj, extraInfoEl, content) {
		nextEl.remove();
		extraInfoEl.insertAdjacentHTML('afterend', obj.generateCommentContentMarkup(content));
		
		if (obj.hasClass(obj, extraInfoEl.lastElementChild, "editedTimeEl"))
			extraInfoEl.lastElementChild.insertAdjacentHTML("beforebegin", obj.generateEditBtnMarkup());
		else
			extraInfoEl.insertAdjacentHTML('beforeend', obj.generateEditBtnMarkup());
	}
	
	// 編輯留言 框的 click handler(事件委派)
	async editCommentClickHandler(obj, extraInfoEl, content, e) {
		const t = e.target;
		const tagName = t.tagName.toLowerCase();
		
		// 停止事件冒泡，不會再將 click 事件往外面的父元素傳遞
		e.stopPropagation();
		
		// 取消編輯留言 button 處理
		if(tagName === "button" && obj.hasClass(obj, t, "replyCancelBtn")) {
			obj.removeEditComment(this, obj, extraInfoEl, content);
		}
		// 送出編輯留言 button 處理
		else if(tagName === "button" && obj.hasClass(obj, t, "replySubmitBtn")) {
			// 取得父元素，有可能是父留言，也可能是子留言，因此要 OR
			const comment = this.closest(".sub-comment") || this.closest(".main-comment");
			const textarea = this.querySelector("textarea");
			const url = CourseComment.UPDATE_URL.format(comment.id);
			
			// 移除編輯留言框
			obj.removeEditComment(this, obj, extraInfoEl, textarea.value);
			
			// send PATCH request
			const data = await obj.resultHandler("PATCH", url, {content: textarea.value}, obj, undefined, false);
			
			if (data)
				console.log(data);
		}
	}
	
  // 從後端回傳的結果統一呼叫這 method
  async resultHandler(method, url, payload, obj, element, spinner = true) {
		let result;
		
	try {
			if(spinner)
  	  	obj.showSpinner(element);
      result = await CourseComment.ajax(method, url, payload);
    } catch (error) {
      console.error(error);
    } finally {
			if(spinner)
      	obj.removeSpinner();
    }
    
    return result;
	}
	
  // 滾輪事件的 handler(處理-確認抵達了購課前問答那塊)
  async observerHandler(obj, entries, observer) {
    const [entry] = entries;

    if (!entry.isIntersecting) return;

    // 向後端要資料
    const result = await obj.resultHandler("GET", obj.commentsUrl, undefined, obj);
    
    if (result)
    	obj.firstTimeLoadPage(result);
    
    // 之後就可以解除監聽 留言板綠色大圈圈只會初次進來轉一次就取消轉圈圈
    observer.unobserve(entry.target);
  }
  
  // 固定在最上面的新增留言框 click handler；固定最上面的輸入留言欄給人做輸入 
  // "!" 等於特例 可以標題空白不用輸入；textarea 留言輸入框
  async firstItemSubmitBtnClickHandler(obj, e) {
		const url = CourseComment.CREATE_URL.format(obj.cid);
		const textarea = obj.firstItem.querySelector("textarea");
		const data = await obj.resultHandler("POST", url, {
			title: "!",             
			type: "course",
			content: textarea.value
		}, obj);
		
		//送出留言後留言的字會清掉 恢復成原來空白的留言欄
		if (data) {
			textarea.value = "";
			e.target.disabled = true;

      // 新增留言成功後，要將元件新增到頁面上，會新增到最上面，但在第一個元素之後；新增留言後，那筆留言會到輸入留言欄那塊底下成為第二筆留言
			obj.firstItem.insertAdjacentHTML('afterend', obj.generateMainCommentMarkup(data.result));
			obj.showToast(CourseComment.CREATED_MSG);
		}
	}
	
  // 當留言 button 按下(送出留言)時的 handler(指按"留言"送出這個動作)
  async replySubmitBtnClickHandler(obj, e) {
		const url = CourseComment.CREATE_URL.format(obj.cid);
		const replyComment = e.target.closest(obj.elements.replyCommentEl);
		const comment = replyComment.closest(obj.elements.commentEl);
		
    // 如果是回覆別人的留言，要將父留言的 id 抓出來，會一起傳送到後端
		const data = await obj.resultHandler("POST", url, {
			title: "!",
			type: "course",
			content: replyComment.querySelector("textarea").value,
			uuid: comment.id.trim(),
		}, obj);
		
		if (data) {
			const lastItem = comment.lastElementChild;
			
      // 因為是回覆別人留言，因此畫面會添加在父元素的最後
			lastItem.insertAdjacentHTML('beforebegin', obj.generateSubCommentMarkup(data.result));
			obj.removeReplyCommentElement(obj, lastItem);
			obj.showToast(CourseComment.CREATED_MSG);
		}
	}
	
  // textarea 的 handler
  textareaKeyUpHandler(obj, e) {
    const comment = e.target.closest(".comment-wrap");
    const replySubmitBtn = obj.getEl("replySubmitBtn", comment);

    if (e.target.value.trim().length !== 0) replySubmitBtn.disabled = false;
    else replySubmitBtn.disabled = true;
  }
  
  // 載入更多留言的 click event
  async loadMoreBtnClickHandler(obj) {
		const data = await obj.resultHandler("GET", obj.commentsUrl, undefined, obj, obj.loadMoreEl);
		
		obj.loadMoreEl.remove();
		obj.addCommentElements(data);
	}
	
  // 動態創建 Template String，因為會要將後端回傳的資料添加上去，這裡是父留言
  //502行 fill就是實心愛心、heart-o就是空心愛心判斷使用哪一個
  generateMainCommentMarkup(data, username) {
    const subComments = data.comments;

    return `
    <div id="${data.uuid}" class="animate__animated animate__fadeInDown main-comment isolated-comment-item pad-rl-20 pad-tb-20 marg-b-20">
      <div class="main-comment-block marg-t-20">
        <div>

          <div class="avatar profile-image profile-image-sm">
            <a class="" href="javascript:void(0);">
            	<img src="/SpecialTopic/img/default_avatar.png" width="" class=" aratar--img loaded">
          	</a>
          </div>

          <div class="comment-content-block comment-wrap">
            <!-- Username & comment post time block-->
            <div class="comment-extra-info text-sm">
              <div class="first-row">
              	${Boolean(data.isInstructor) ? '<span class="tag-gray marg-r-5">授課老師</span>' : ""}
                <a href="javascript:void(0);" class="username">${data.member.username}</a>
              </div>

              <a class="comment-post-time" href="javascript:void(0);" title="${data.postTime}">
                <span><span class="hidden-xxs">．</span><time class="time">
                ${moment(data.postTime).fromNow()}</time></span>
              </a>
              
              <div class="heart-container hh-like">
              	<span theme="white" aria-hidden="true" 
              				class="heart-icon fa ${data.liked ? 'fa-heart heart--filled' : 'fa-heart-o heart'}"></span>${data.likeCount}
              </div>
              
              ${data.isOwner ? this.generateEditBtnMarkup() : ""}
              ${data.postTime !== data.editTime ? this.generateEditdTimeMarkup(data.editTime) : ""}
            </div>

            <div>
              <div class="comment-content-text-block comment-content">
                ${data.content}
              </div>
            </div>
          </div>
        </div>
      </div>

      ${subComments ? subComments.map((x) => this.generateSubCommentMarkup(x, username)).join("") : ""}
      ${username ? this.generateReplyBtnMarkup() : ""}
      
    </div>
    `;
  }

  // 子留言的 Template String，因可能有多個子留言，所以會在上方的 method 被循環呼叫多次
  generateSubCommentMarkup(data) {	
    return `
    <div id=${data.uuid} class="sub-comment marg-t-20">
      <div>
        <div class="sub-comment__img profile-image profile-image-xs" size="40">
          <a href="iavascript:void(0);">
            <img src="/SpecialTopic/img/default_avatar.png" width="" class=" aratar--img loaded">
          </a>
        </div>

        <div class="comment-content-block comment-wrap">
          <div class="username-comment-and-post-time-block comment-extra-info text-sm">
            <div class="first-row">
              ${data.isInstructor ? '<span class="tag-gray marg-r-5">授課老師</span>' : ""}
              <a href="javascript:void(0);" class="username">${data.member.username}</a>
            </div>

            <a class="comment-post-time" href="javascript:void(0);" title="${data.postTime}">
              <span><span class="hidden-xxs">．</span><time class="time">
              ${moment(data.postTime).fromNow()}</time></span>
            </a>
            
            <div class="heart-container hh-like">
            	<span theme="white" aria-hidden="true" 
            				class="heart-icon fa ${data.liked ? 'fa-heart heart--filled' : 'fa-heart-o heart'}"></span>${data.likeCount}
            </div>
            
            ${data.isOwner ? this.generateEditBtnMarkup() : ""}
            ${data.postTime !== data.editTime ? this.generateEditdTimeMarkup(data.editTime) : ""}
          </div>

          <div class="comment-content-text-block">
            <div class="comment-content">
              ${data.content}
            </div>
          </div>
        </div>
      </div>
    </div>
    `;
  }
	
	// 產生 "載入更多留言" button 的 Template String
	generateFakeCommitItemMarkup() {
		return `
			<div class="fake-comment-block marg-t-20">
				<div class="fake-comment-item isolated-comment-item pad-rl-20 pad-tb-20">
					<div class="avatar profile-image profile-image-sm" size="56">
						<a class="no-pointer" href="javascript:void(0);">
							<div style="height: 100%;">
								<img src="/SpecialTopic/img/default_avatar.png" width="" class=" aratar--img loaded">
							</div>
						</a>
					</div>
					
					<div class="comment-wrap">
						<div class="comment-extra-info"></div>
						<div class="comment-content marg-t-10"></div>
					</div>
				</div>
				
				<div class="text-center">
					<button class="fake-item__button relative marg-t-50">載入更多留言</button>
				</div>
			</div>
		`;
	}
	
	// 產生 "修改" button 的 Template String
	generateEditBtnMarkup() {
		return `
    <span class="btn-edit marg-l-10 pseudo-btn">
    	<span aria-hidden="true" class="fa fa-pencil fa-fw"></span>
    	修改
    </span>
		`;
	}
	
	// 產生 修改留言框 的 Template String
	generateEditCommentMarkup(content) {
		return `
		<div class="animate__animated animate__fadeIn">
	    <div class="comment-content-edit" style="height: auto;">
	      <textarea id="commentTextarea" placeholder="留言" class="pad-t-10 pad-r-10 pad-b-10 pad-l-10" value="">${content}</textarea>
	    </div>
	
	    <div class="text-right" style="opacity: 1;">
	      <div class="comment-description">
	        <div class="sc-1xf9k5m-1 button-wrapper">
	        <button class="button--cancel reply-btn-cancel btn-hollow btn-sm marg-r-10">取消</button>
	          <button class="button--submit" disabled>修改</button>
	        </div>
	      </div>
	    </div>
    </div>
		`;
	}
	
	// 產生 留言內容 的 Template String
	generateCommentContentMarkup(content) {
		return `
		<div>
			<div class="comment-content">${content}</div>
		</div>
		`;
	}
	
	// 產生 已編輯時間 的 Template String
	generateEditdTimeMarkup(time) {
		return `
		<a class="edited-time" href="javascript:void(0);" title="最後編輯時間: ${time}">
	    <span><span class="hidden-xxs">．</span>已編輯</span>
	  </a>
		`;
	}
	
	generateReplyBtnMarkup() {
		return `
		<div class="reply-block">
      <hr class="marg-b-10">
      <span class="reply-btn reply-text pseudo-btn"><i class="bi bi-reply-fill pad-r-5"></i>回覆</span>
    </div>
		`;	
	}
	
  // 最後產生的總 Template String 會在這被 join 後回傳
  generateCommentsMarkup(data) {
		let markup = data.result.map((x) => this.generateMainCommentMarkup(x, data.username)).join("");
		
		if (data.hasNext)
			return markup += this.generateFakeCommitItemMarkup();
		
    return markup;
  }
	
	// 第一次做資料載入的動作會執行此方法，主要是設定使用者名撐與顯示訊息
	firstTimeLoadPage(data) {
		const username = data.username
		const result = data.result
		
		this.setFirstItemUsername(username);
		
    if (!result || result.length === 0) {
    	this.showToast("此課程還沒有人做詢問！<br>您有任何問題都歡迎發問唷～");
    	return;
		}
		
		this.addCommentElements(data);
	}
	
  // 後端回傳資料後，要開始產生 Template String 並添加到頁面上
  addCommentElements(data) {
    const template = this.generateCommentsMarkup(data);

    this.commentsBody.insertAdjacentHTML("beforeend", template);
    
    if (data.hasNext)
			this.cbt = data.cbt;
  }
}

// Entry point
$(document).ready(() => {
  new CourseComment();
})
