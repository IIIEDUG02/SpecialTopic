<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div class="comments-container">
  <div class="comments-wrapper">

    <div>
      <h4 class="observer pb-mb-border-b">購課前問答</h4>

      <!-- All comments block -->
      <div class="comments marg-t-20">
        <!-- Post comment block -->
        <sec:authorize access="isAuthenticated()">
          <div class="first-level-isolated-comment-item isolated-comment-item pad-rl-20 pad-tb-20 marg-b-20">
            <div>
              <!-- Profile avatar -->
              <div class="avatar profile-image profile-image-sm ">
                <a class="" href="javascript:void(0);">
                  <img id="avatar" src="/SpecialTopic/img/default_avatar.png" width="" class="aratar--img loaded">
                </a>
              </div>
  
              <!-- Comment content block -->
              <div class="comment-wrap">
                <!-- Username -->
                <div class="comment-extra-info text-sm">
                  <div class="first-row">
                    <a id="firstItemUsername" href="javascript:void(0);" class="username"></a>
                  </div>
                </div>
  
                <!-- content text -->
                <div class="comment-content-edit" style="height: auto;">
                  <textarea id="commentTextarea" placeholder="留言" class="pad-t-10 pad-r-10 pad-b-10 pad-l-10"></textarea>
                </div>
  
                <!-- comment description block -->
                <div class="text-right" style="opacity: 1;">
                  <div class="comment-description">
                    <!-- 留言 button block -->
                    <div class="button-wrapper">
                      <button class="first-item-submit-btn button--submit" disabled>留言</button>
                    </div>
  
                    <!-- description text block -->
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
        </sec:authorize>
      </div>
    </div>
  </div>
</div>