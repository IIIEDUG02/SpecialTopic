/**
 * Author: Anna
 * 
 * 課程頁面留言都是以動態的方式來操作，因此會有很多 Template String，看起來會較凌亂。
 * 若之後有學習到前端框架，可將這些寫成 Component。
 * 
 */
class CourseComment extends Base {
	static COMMENTS_URL = `/SpecialTopic/api/comment/comments?cid={0}&type=course&pageNum={1}&pageSize={2}`
	static CREATE_URL = `/SpecialTopic/api/comment/create/{0}`;
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
              <button class="button--cancel btn-cancel btn-hollow btn-sm marg-r-10">取消</button>
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
      replyButton: ".reply-btn",
      commentEl: ".main-comment",
      replyCommentEl: ".reply-comment",
      submitBtn: ".button--submit",
      cancelBtn: ".btn-cancel",
      observer: ".observer",
      mask: "#commentMask",
      spinner: "#commentSpinner",
      firstItemUsernameEl: "#firstItemUsername",
      firstItemBtn: ".first-item-submit-btn",
      replyItemUsernameEl: ".reply-username",
      fakeItemEl: ".fake-comment-block",
      fakeItemBtn: ".fake-item__button", 
    };

    this.commentsWrapper = document.querySelector(
      this.elements.commentsWrapper
    );
    this.commentsBody = document.querySelector(this.elements.commentsBody);
    this.username = "";

    // 將會從第零頁開始要資料
    this.pageNum = 0;

    // 向後端要資料時的指定筆數
    this.pageSize = 3;
    
    this.init();
  }
  
  // getter 方法，將網址列的課程代號(cid)取出
  get cid() {
		const cid = location.href.split("/")[location.href.split("/").length - 1];
		
		return isNaN(Number(cid)) ? 0 : Number(cid);
	}
	
	// getter 方法，將 fakeItemEl 動態取出
	get fakeItemEl() {
		return document.querySelector(this.elements.fakeItemEl);
	}
	
	// getter 方法，將 COMMENTS_URL 字串格式化之後回傳
	get commentsUrl() {
		return CourseComment.COMMENTS_URL.format(this.cid, this.pageNum, this.pageSize);
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
	
	// 將 click event 註冊在最外層的 container，之後再用事件委派的方式去呼叫相對應的 event handler
	commentsBodyClickHandler(obj, e) {
		const t = e.target;
		const tagName = t.tagName.toLowerCase();
		const c = t.classList;
		
		if (tagName === 'span' && c.contains(obj.elements.replyButton.slice(1))) {
			obj.addReplyCommentElement(obj, t);
		}
		else if (tagName === 'button' && c.contains(obj.elements.cancelBtn.slice(1))) {
    	const div = t.closest(obj.elements.commentEl).lastElementChild;
			obj.removeReplyCommentElement(obj, div);
		}
		else if (tagName === 'button' && c.contains(obj.elements.firstItemBtn.slice(1))) {
			obj.firstItemBtnClickHandler(obj, e);
		}
		else if (tagName === 'button' && c.contains(obj.elements.submitBtn.slice(1))) {
			obj.submitBtnClickHandler(obj, e);
		}
		else if (tagName === 'button' && c.contains(obj.elements.fakeItemBtn.slice(1))) {
			obj.fakeItemBtnClickHandler(obj);
		}
	}
	
	// 初始化第一個 Item，也就是留言版區塊的最上面的一個固定元素(就是想要新增留言的區塊)
  initFirstItem() {
    const firstItem = this.firstItem;

    if (firstItem) {
      firstItem
        .querySelector("textarea")
        .addEventListener("keyup", this.textareaKeyUpHandler.bind(null, this));
    }
  }

  // 用來監聽滾輪滑動，到一定位置時才將留言加載到畫面上
  initIntersectionObserverListener() {
    const target = document.querySelector(this.elements.observer);
    const observer = new IntersectionObserver(this.observerHandler.bind(null, this), {
      root: null,
      threshold: 1,
    });

    observer.observe(target);
  }
  
  // 動態設定使用者的名字
  setFirstItemUsername(username) {	
    if (username) {
			const usernameEl = this.firstItem.querySelector(this.elements.firstItemUsernameEl);
			
			if (usernameEl)
				this.username = usernameEl.innerText = username;
		}
	}
	
	// 設定回覆框上面的名字
	setReplyItemUsername(el) {
		const replyUsernameEl = el.querySelector(this.elements.replyItemUsernameEl);
    const usernameEl = document.querySelector(this.elements.firstItemUsernameEl);
    
    replyUsernameEl.innerText = usernameEl.innerText;
	}
	
	// 險示載入中的元件
  showSpinner(element) {
		if (element) {
			element.insertAdjacentHTML("beforeend", this.spinner);
			document.querySelector(this.elements.mask).style.background = "#fff";
		} else {
			this.commentsWrapper.insertAdjacentHTML("beforeend", this.spinner);
		}
  }
  
	// 移除載入中的元件
  removeSpinner() {
    const mask = document.querySelector(this.elements.mask);
    const spinner = document.querySelector(this.elements.spinner);

    mask.remove();
    spinner.remove();
  }
	
  // 回覆 button 的 handler，點擊按鈕後將回覆框動態增加到頁面上
  addReplyCommentElement(obj, el) {
    // 往外層取得指定的 parent element
    const mainComment = el.closest(obj.elements.commentEl);

    // 之後取得最後一個元素，也就是包住回覆 button 的 div
    const div = mainComment.lastElementChild;

    mainComment.querySelector(obj.elements.replyButton).remove();

    // 將回覆框動態添加到頁面上
    div.insertAdjacentHTML("beforeend", obj.replyCommentTemplate);
    
    // 之後為回覆框上面的元素添加事件
    const replyComment = mainComment.querySelector(obj.elements.replyCommentEl);
    const textarea = replyComment.querySelector("textarea");
    
    // 設定使用者名字
    obj.setReplyItemUsername(replyComment);
    
    // 監聽 textarea 輸入，若為空則不能按送出
    textarea.addEventListener(
      "keyup",
      obj.textareaKeyUpHandler.bind(null, obj)
    );
  }
  
  // 從頁面上移除回覆框元件
  removeReplyCommentElement(obj, div) {
    div.querySelector(obj.elements.replyCommentEl).remove();
    div.insertAdjacentHTML("beforeend", obj.replyButtonTemp);
  }
	
  // 從後端回傳的結果統一呼叫這 method
  async resultHandler(obj, url, payload, element) {
		let result;
		
		try {
			obj.showSpinner(element);
      result = await CourseComment.ajax(url, payload);
    } catch (error) {
      console.error(error);
    } finally {
      obj.removeSpinner();
    }
    
    return result;
	}
	
  // 滾輪事件的 handler
  async observerHandler(obj, entries, observer) {
    const [entry] = entries;

    if (!entry.isIntersecting) return;

    // 向後端要資料
    const result = await obj.resultHandler(obj, obj.commentsUrl);
    
    if (result)
    	obj.firstTimeLoadPage(result);
    
    // 之後就可以解除監聽
    observer.unobserve(entry.target);
  }
  
  // 固定在最上面的新增留言框 click handler
  async firstItemBtnClickHandler(obj, e) {
		const url = CourseComment.CREATE_URL.format(obj.cid);
		const firstItem = obj.firstItem;
		const textarea = firstItem.querySelector("textarea");
		const data = await obj.resultHandler(obj, url, {
			title: "!",
			type: "course",
			content: textarea.value
		});
		
		if (data) {
			textarea.value = "";
			e.target.disabled = true;

      // 新增留言成功後，要將元件新增到頁面上，會新增到最上面，但在第一個元素之後
			firstItem.insertAdjacentHTML('afterend', obj.generateMainCommentMarkup(data.result));
			obj.showToast(CourseComment.CREATED_MSG);
		}
	}
	
  // 當留言 button 按下(送出留言)時的 handler
  async submitBtnClickHandler(obj, e) {
		const url = CourseComment.CREATE_URL.format(obj.cid);
		const commentEl = e.target.closest(obj.elements.replyCommentEl);
		const mainCommentEl = commentEl.closest(obj.elements.commentEl);
		
    // 如果是回覆別人的留言，要將父留言的 id 抓出來，會一起傳送到後端
		const data = await obj.resultHandler(obj, url, {
			title: "!",
			type: "course",
			content: commentEl.querySelector("textarea").value,
			uuid: mainCommentEl.id.trim(),
		});
		
		if (data) {
			const lastItem = mainCommentEl.lastElementChild;
			
      // 因為是回覆別人留言，因此畫面會添加在父元素的最後
			lastItem.insertAdjacentHTML('beforebegin', obj.generateSubCommentMarkup(data.result));
			obj.removeReplyCommentElement(obj, lastItem);
			obj.showToast(CourseComment.CREATED_MSG);
		}
	}
	
  // textarea 的 handler
  textareaKeyUpHandler(obj, e) {
    const comment = e.target.closest(".comment-wrap");
    const submitBtn = comment.querySelector(obj.elements.submitBtn);

    if (e.target.value.trim().length !== 0) submitBtn.disabled = false;
    else submitBtn.disabled = true;
  }
  
  // 載入更多留言的 click event
  async fakeItemBtnClickHandler(obj) {
		const data = await obj.resultHandler(obj, obj.commentsUrl, undefined, obj.fakeItemEl);
		
		obj.fakeItemEl.remove();
		obj.addCommentElements(data);
	}
	
  // 動態創建 Template String，因為會要將後端回傳的資料添加上去，這裡是父留言
  generateMainCommentMarkup(data) {
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
            </div>

            <div>
              <div class="comment-content-text-block comment-content">
                ${data.content}
              </div>
            </div>
          </div>
        </div>
      </div>

      ${subComments ? subComments.map((x) => this.generateSubCommentMarkup(x)).join("") : ""}

      <div class="reply-block">
        <hr class="marg-b-10">
        <span class="reply-btn reply-text pseudo-btn"><i class="bi bi-reply-fill pad-r-5"></i>回覆</span>
      </div>
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
	
  // 最後產生的總 Template String 會在這被 join 後回傳
  generateCommentsMarkup(data) {
		let markup = data.result.map((x) => this.generateMainCommentMarkup(x)).join("");
		
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
			this.pageNum += 1;
  }
}

// Entry point
$(document).ready(() => {
  new CourseComment();
})
