<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 將 Toast 元件寫成 jsp，依照需求自行 include -->
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
  <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="background-color: #5fcf80 !important; color: #fff !important;">
    <div class="toast-header">
    <i class="bi bi-exclamation-circle" style="margin-right: 5px;"></i>
      <strong class="me-auto">提醒</strong>
      <small>now</small>
      <!-- Toast close button -->
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    
    <!-- Toast body -->
    <div id="toast-body" class="toast-body">  
    </div>
  </div>
</div>
