class CourseComment extends Base {
	static COMMENTS_URL = `/SpecialTopic/api/comment/comments?cid={0}&type=course&pageNum={1}&pageSize={2}`
	
  constructor() {
    super();

    this.replyCommentTemplate = `
    <div class="sub-comment reply-comment">
      <div>
        <div class="dOurpm profile-image profile-image-xs">
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
    this.replyButtonTemp = `
    <span class="reply-btn reply-text pseudo-btn"><i class="bi bi-reply-fill pad-r-5"></i>回覆</span>
    `;
    this.spinner = `
    <div id="commentMask" class="mask"></div>
    <div id="commentSpinner" class="spinner-border text-secondary" role="status"
          style="color: #5fcf80 !important; position: absolute; left: calc(50% - 5rem / 2); 
          top: calc(50% - 5rem / 2); width: 5rem; height: 5rem; z-index: 9999;"
    >
      <span class="visually-hidden">Loading...</span>
    </div>
    `;
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
      replyItemUsernameEl: ".reply-username",
    };
    this.commentsWrapper = document.querySelector(
      this.elements.commentsWrapper
    );
    this.commentsBody = document.querySelector(this.elements.commentsBody);
    this.username = "";
    this.pageNum = 0;
    this.pageSize = 5;
    
    this.init();
  }
  
  get cid() {
		const cid = location.href.split("/")[location.href.split("/").length - 1];
		
		return isNaN(Number(cid)) ? 0 : Number(cid);
	}

  init() {
    moment.lang("zh-tw");

    this.initFirstItem();
    this.initIntersectionObserverListener();
  }
	
	
  initFirstItem() {
    const firstItem = document.querySelector(this.elements.firstItem);

    if (firstItem) {
      firstItem
        .querySelector("textarea")
        .addEventListener("keyup", this.textareaKeyUpHandler.bind(null, this));
    }
  }

  initReplyButtonClickListener() {
    const replyBtns = [...document.querySelectorAll(this.elements.replyButton)];

    replyBtns.forEach((e) => {
      e.addEventListener("click", this.addReplyCommentElement.bind(null, this));
    });
  }

  initIntersectionObserverListener() {
    const target = document.querySelector(this.elements.observer);
    const observer = new IntersectionObserver(this.observerHandler.bind(null, this), {
      root: null,
      threshold: 1,
    });

    observer.observe(target);
  }
  
  setFirstItemUsername(username) {		
    if (username) {
			const usernameEl = document.querySelector(this.elements.firstItemUsernameEl);
			
			if (usernameEl)
				this.username = usernameEl.innerText = username;
		}
	}
	
	setReplyItemUsername(el) {
		const replyUsernameEl = el.querySelector(this.elements.replyItemUsernameEl);
    const usernameEl = document.querySelector(this.elements.firstItemUsernameEl);
    
    replyUsernameEl.innerText = usernameEl.innerText;
	}
	
  showSpinner() {
    this.commentsWrapper.insertAdjacentHTML("beforeend", this.spinner);
  }

  removeSpinner() {
    const mask = document.querySelector(this.elements.mask);
    const spinner = document.querySelector(this.elements.spinner);

    this.commentsWrapper.removeChild(mask);
    this.commentsWrapper.removeChild(spinner);
  }
  
  async observerHandler(obj, entries, observer) {
    const [entry] = entries;

    if (!entry.isIntersecting) return;

    const url = CourseComment.COMMENTS_URL.format(obj.cid, obj.pageNum, obj.pageSize);

    try {
			obj.showSpinner();
			
      const result = await CourseComment.ajax(url);

      obj.addCommentElements(result);
    } catch (error) {
      console.error(error);
    } finally {
      obj.removeSpinner();
    }

    observer.unobserve(entry.target);
  }

  addReplyCommentElement(obj, event) {
    const mainComment = event.target.closest(obj.elements.commentEl);
    const div = mainComment.children[mainComment.children.length - 1];

    div.removeChild(mainComment.querySelector(obj.elements.replyButton));
    div.insertAdjacentHTML("beforeend", obj.replyCommentTemplate);
    
    
    const replyComment = mainComment.querySelector(obj.elements.replyCommentEl);
    
    const cancelBtn = replyComment.querySelector(obj.elements.cancelBtn);
    const textarea = replyComment.querySelector("textarea");
    
    obj.setReplyItemUsername(replyComment);
    cancelBtn.addEventListener(
      "click",
      obj.removeReplyCommentElement.bind(null, obj, div)
    );

    textarea.addEventListener(
      "keyup",
      obj.textareaKeyUpHandler.bind(null, obj)
    );
  }

  removeReplyCommentElement(obj, div) {
    div.removeChild(div.querySelector(obj.elements.replyCommentEl));
    div.insertAdjacentHTML("beforeend", obj.replyButtonTemp);

    const replyBtn = div.querySelector(obj.elements.replyButton);

    replyBtn.addEventListener(
      "click",
      obj.addReplyCommentElement.bind(null, obj)
    );
  }

  textareaKeyUpHandler(obj, e) {
    const comment = e.target.closest(".comment-wrap");
    const submitBtn = comment.querySelector(obj.elements.submitBtn);

    if (e.target.value.trim().length !== 0) submitBtn.disabled = false;
    else submitBtn.disabled = true;
  }

  generateMainCommentMarkup(data) {
    const subComments = data.comments;

    return `
    <div id="${data.uuid}" class="main-comment isolated-comment-item pad-rl-20 pad-tb-20 marg-b-20">
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
              ${Boolean(data.isInstructor) ? '<span class="tag-gray marg-r-5">授課老師</span>' : ""}
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

  generateCommentsMarkup(data) {
    return data.map((x) => this.generateMainCommentMarkup(x)).join("");
  }

  addCommentElements(data) {
		const username = data.username
		const result = data.result
		
		this.setFirstItemUsername(username);
		
    if (!result || result.length === 0)
      return;

    const template = this.generateCommentsMarkup(result);

    this.commentsBody.insertAdjacentHTML("beforeend", template);
    this.initReplyButtonClickListener();
  }
}

// Entry point
$(document).ready(() => {
  new CourseComment();
})
