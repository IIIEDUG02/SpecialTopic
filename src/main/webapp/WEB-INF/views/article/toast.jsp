<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 9999">
  <div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="background-color: #5fcf80 !important; color: #fff !important;">
    <div class="toast-header">
    <i class="bi bi-exclamation-circle" style="margin-right: 5px;"></i>
      <strong class="me-auto">提醒</strong>
      <small>now</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div id="toast-body" class="toast-body">
      
    </div>
  </div>
</div>

<script>
  function showToast(message) {
  		body.innerText = message
  		new bootstrap.Toast(toast).show()
  }
</script>