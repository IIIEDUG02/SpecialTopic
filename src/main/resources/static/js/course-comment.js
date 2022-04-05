class CourseComment extends Base {
  constructor() {
    super();

    this.replyCommentTemplate = `
    <div class="sub-comment reply-comment">
      <div>
        <div class="dOurpm profile-image profile-image-xs">
          <a class="" href="javascript:void(0);">
            <img
              src="data:image/png;base64,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"
              srcset="data:image/png;base64,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 1x, data:image/png;base64,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 2x"
              width="" class="sc-cx4oas-0 aratar--img  loaded">
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
      replyUsernameEl: ".reply-username",
    };
    this.commentsWrapper = document.querySelector(
      this.elements.commentsWrapper
    );
    this.commentsBody = document.querySelector(this.elements.commentsBody);
    this.pageNum = 0;
    this.pageSize = 5;
    this.init();
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

    const cid = location.href.split("/")[location.href.split("/").length - 1];
    const url = `
    /SpecialTopic/api/comment/comments?cid=${cid}&type=course&pageNum=${obj.pageNum}&pageSize=${obj.pageSize}
    `;

    obj.showSpinner();

    try {
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
    
    const usernameEl = document.querySelector(obj.elements.firstItemUsernameEl);
    const replyComment = mainComment.querySelector(obj.elements.replyCommentEl);
    const replyUsernameEl = replyComment.querySelector(obj.elements.replyUsernameEl);
    const cancelBtn = replyComment.querySelector(obj.elements.cancelBtn);
    const textarea = replyComment.querySelector("textarea");
    
    replyUsernameEl.innerText = usernameEl.innerText;
    
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
    <!-- Comment block -->
    <div id="${data.uuid}" class="main-comment isolated-comment-item pad-rl-20 pad-tb-20 marg-b-20">
      <!-- Main comment block -->
      <div class="marg-t-20">
        <div>
          <!-- Profile avatar -->
          <div class="avatar profile-image profile-image-sm">
            <a class="" href="javascript:void(0);"><img
                src="data:image/png;base64,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"
                srcset="data:image/png;base64,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 1x, data:image/png;base64,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 2x"
                width="" class=" aratar--img loaded"></a>
          </div>

          <!-- Comment content block -->
          <div class="comment-wrap">
            <!-- Username & comment post time block-->
            <div class="comment-extra-info text-sm">
              <div class="first-row">
                <a href="javascript:void(0);" class="username">${data.member.username}</a>
              </div>

              <!-- Comment post time -->
              <a href="javascript:void(0);" title="${data.postTime}">
                <span><span class="hidden-xxs">．</span><time class="time">
                ${moment(data.postTime).fromNow()}</time></span>
              </a>
            </div>

            <!-- Comment content text block -->
            <div>
              <div class="comment-content">
                ${data.content}
              </div>
            </div>
          </div>
        </div>
      </div>

      ${
        subComments
          ? subComments.map((x) => this.generateSubCommentMarkup(x)).join("")
          : ""
      }

      <!-- 回覆 block -->
      <div>
        <hr class="marg-b-10">
        <span class="reply-btn reply-text pseudo-btn"><i class="bi bi-reply-fill pad-r-5"></i>回覆</span>
      </div>
    </div>
    `;
  }

  generateSubCommentMarkup(data) {
    return `
    <!-- Sub comments block -->
    <div id=${data.uuid} class="sub-comment marg-t-20">
      <div>
        <!-- Profile avatar -->
        <div class="dOurpm profile-image profile-image-xs" size="40">
          <a href="iavascript:void(0);">
            <img
              src="data:image/png;base64,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"
              srcset="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKoAAACqCAMAAAAKqCSwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAYBQTFRFmazEKVuTNTU1br2eIUp9tcTWLGGcz9nl6/Py8/r4MWuqI02AJFCFPYxwFFWdZr+gprnQSnqvOXZgQJt7kaS+jIqKN2ZWip21NFZKQqeEJ1WMSLKM6O3zis62WDQ8LmSh4+bruLa3comnJGKkJVKHSW2YZLmZa2hjUk9PqzJMT7WR2+HqXIWzGlqgJlOKVbaTGkuEG0J0LEM7J1aOI06CfqHJK16YIkt+lDNIcjNBL2ekMGinLmmoQ2OLKGSme5OyKFiQH16iK1GCO2uhXbmXIlmVn52dPJmEC06XccGkHVWTWnicDlKbcVZYZ4KkN1qJJl2abYyxNV6QHVKOK2anW32mI1KJHD5sIh4fcsSoodjF0OviJE+D3PDpxOfbW7qZldO9fsmvueLTrd3MJCclxs/aVG6QabycWbeVyMfIJjAsOFV+YnqaIEd5KUl1LWKe1tbWKDkzQXavZJC/t9/RvM7jKF912OPvMCwtEkiIUaGDS5SNUa2TIUl7RLGL////rKUQCwAACTxJREFUeNrs2olbGkkWAPAWgwIGUUhQECSjiKaNmvGMEhUT51jN5DTbAgHxSEwy2XU2e8yxS/GvT/V9UF1dXV2NuB/v+/IloD5+VFe991rCtW5McD1qj9qj9qg9ao/ao/aoPWqPyirm78zM7Ia6nzo4td0U4wlTLHvqyPMnTS2ePJ/vUmqIO2wa4xYMZliO5fbcblpCpG5sHAa7ihqcetFsD5m6MTQT7BJqyLQ9UdShoW3u+qnzlu1poW5syNShoRfctVIHd7ebuDBSV1c91i4P1BHk9rSjrkLru7QXLEdbley2J5YK4+18J6nzdw6bZCFThzZk6jvRevs2LdY1VW2arqmrEvU2jDxd7XJHHXn+oukmlFplpeapsJyL7Xn4pOkytLJqpebzrzm/qIMzTYrAUBddY0m//bDJgPrOSIVR2s34QI1E1ldppFiqOyw5lQLrTE2n08S1yw01UuzbYEDNm6krK4RYjuzwh9YiFFhHalqirqwQ1S4S6r2fBa6VUbEltx2AgFoqEWBJqO8FYQz+lfmnjN1Ks6DmzdSV0tMMOyosrip20TVVnVbsqaVXDKhBlQqxD2TsVZ6Cuoqh9l0VRxlQxwThve4eJcciByu1WRmopasizLjGmqpjncuszWBlppbWt+R8QfZUOGB9JcNiRwCJurKee/w48hj+iUQGGVC/CMI98zNhFYsvsw7UdB90KvFgJMOiWH0QhLbnwl8JegKOutg3oDlHRzKMWgCK2lJKgYh1P1hB59HR0eMjMUbXfmU3A2yL1PDZKQ8AOK3q1GIE3xPsppXSyx3RuLOzc/SV1ElIFYQPrQpQIys/CctAsYjHIqkrL3fUiKwNtlwEITUL9KhITz4VqVtbW0X7MttOXXw5PS0ip6enI2v/9uGGRRD+C4xRM1BzOaUqIrAW6l+/25tWYu/A3EVD59lyo+6dOij8xyQFpyZq7iqnYAdW7acV1bkH42BD79RiVMty3ljIK/W98IOZCkLyrK1SrwZUrLknaNR3B6JQYu4d3BLE0HtKqKzl5cNeqZZFBeBcokpbVaYOaFhjmZWpq28WJOPewsLCwUNBCY1a5Q2Jyx6pQeuiShnN1PX19VxbT4DUoTcQuCAxofNbQbBSz8yZz7xRx0BbwA2sUa8UqgGrUt8cLMgxPGxyClqnrloSn3ijFtqptdZIG7UPRk4vs7cevhkeHpaYw8MWp6C2vxBvzVz3RP1fO7Vuog5oVG2g2xoYluPZs2e/tzk1arktc9YLNdwuhV3AhgrHeQUrO39EOlXqWXtm3gsVkQ80pBEASV1J94kNbAcybZ1ipxYXgUekrnqgZhH5yq01eyqc74qRPYxTpVYA8oLRU2NIavhB0Z66mO97KGDjg92iyq2QjoraqlJNybxto4pSmZrHS4UpmOAc2KWmpFaRCeXhYNTQAYzUvCNVHAFOgH1qKmoFly/4lJLK2a0B5lw5Uk/hTyeSyWQ8asynzRUjT2moH2yOqzeqeJnGj6VYjqcQ+bgcgjqEcf68e89yqBJRGAmnEsCRnKrlYyUukinEWx/LkVP/PjVoLteJ8X8caxH1QpV21Gc92UUC9dZfDZBRvwSt5Tp+bIworrU6UaWKIucJBHRrw/JtmVdmahPh3B4xTvny9R83SY8TuJmVIzhVEjXwS6v1aQ7+4zN6Bs68VTqARP1b2wYdM/+Cpq6t6d1vvp9TpJ+BF6pU/C5gmm/ER5/Uq4Qq1PNv7ajaBtWjIeZNXSh5Feu4F6rcq8Rj9Yv0WEyYtC3UwRkU9UvQbgnERW1pSyBffxCjpFa1HSW9+3tiwjim+gVn5L76LXqD6vd+Ut6kmTqOb1ccSa+Kwjy/fYLSOe29n9v9xJ3XOtW6QS2nVaJ+Lz6cNRQXWuqp/NMXhlO6DFAlwJjytURFbFBrXqlSzd29K2/VOPBEVUaKpKWgYHaUWAx208JUkGBcSxmXIA48UbUJUO9XTu+dKOrA2gC0vLRUbfpJKdblhPONBUFoo4pq1fPSUg0TYHR8eTmZILmxIAh9VBFHgM/LUZKBlXN9X+V4Y+EcNYALOmqMIiNBNHyg4jJWWVx/ZlTshWp4Pv8sqWe4jDFqahkrpRtXKticIV8OFSUV//bPfVlUSuoJ8GEHVIEfVIekNRpp+MQPqsOmoqsBDUCblaMvKg4fiNBdfsqb64pT1jP2l5+SmnXKeuKaegr8oZYd09ZdSs+dpfY5OfoC4PSRmPtzSv/rNS9LgNyovF/UKkFeN7s1HCORgjAFtU6SuML0SNH+1rpCkpgnHlqywD9qgyhzmbE0RkMtk+WuMJVi3jpHd2Pl9s6FWEpHJU3O1xhK6T4NBKys4Rjwl1oFjKy1E9A9VMBXvfV9ov7HMXoJu8UIld1JqT5kr7h7iRhqE4RdJqGkNty+SNbauEIV3rUUc8PGee0Appp4ps8a4bNTQBOtzlDFfZCtiJGNAcqgoVK/mLegoV6PlL851DIFNXxzqNUetTuoqYR3aoWCWnfrTAZgJFPmZ6PRlP9Ul907EVDC8BFUAqm/bmpqWaUGkqZlhjHeXdR4QA/ps71EUn8i3lXU8YApLA+ZzICsqZubk4H2SPhLLdNQN+/DeCRpJw3qaDdRlZ15X45N2aygu2xVUxJpUqHef6T+oyN71eVkHTeuqh6TbitAzX+qvAUeWalSOXDTA1odoErrOmmRbrqVdoYqtadJj9IOUcXpJD6n7YFHIjQZdZmhY1Qx/pidnf3pp9l4PB6Nuv/pjlL9ugvsNmq5R+1Re9SuoGavhVpp+X4X0KP2qNdHrd8c6vX8JrB+c6hVGmro5lBb/0fUiaWliS6hxiaWCjDsPEv9MJZYU2tU1L/0K/EReWchf41nTG15o/YXEAt7KX/psiuo2X7dir78PiyrZ2r7Hih0FfVfBmq/dQvYfsG/e2tSquWoT6jP73fuJgBDrRmpBfSqXlIu6sS+uIMuPzKitozUfmQBKExQQu1qix9Uqax+pJQWbM8rC2p7W+KpD1TBfgkalFS9B+yzPOdLpqvFE0/WOGqW3xe3ZMH9dZ7gecJFZUSlvbviC9hJxiS11OUOUydstzeKSn6/4vV/sNvPXLaNzLQB9snHVR+oS/34ZeUxDZuWGvK4qh+dzxUP2FBp767sR0dLC7jk3QxWrT8FGACS7ssAQfoGPAAAAABJRU5ErkJggg== 1x, data:image/png;base64,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 2x"
              width="" class=" aratar--img loaded">
          </a>
        </div>

        <!-- Comment content block -->
        <div class="comment-wrap">
          <!-- Username & comment post time block-->
          <div class="comment-extra-info text-sm">
            <div class="first-row">
              ${
                Boolean(data.isInstructor)
                  ? '<span class="tag-gray marg-r-5">授課老師</span>'
                  : ""
              }
              <a href="javascript:void(0);" class="username">${
                data.member.username
              }</a>
            </div>

            <!-- Comment post time -->
            <a href="javascript:void(0);" title="${data.postTime}">
              <span><span class="hidden-xxs">．</span><time class="time">
              ${moment(data.postTime).fromNow()}</time></span>
            </a>
          </div>

          <!-- Comment content text block -->
          <div>
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
		
    if (username) {
			const usernameEl = document.querySelector(this.elements.firstItemUsernameEl);
			
			if (usernameEl)
				usernameEl.innerText = username;
		}
		
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
