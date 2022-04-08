# 流程圖

## 會員管理流程圖
![會員管理流程圖](會員管理製作流程圖.drawio.svg)

## 課程管理
![課程管理構圖](課程流程表.svg)

## 交易系統流程圖
![交易系統構圖](付款流程圖.drawio.svg)

## 互動系統流程圖
![互動系統流程圖](互動平台流程圖.drawio.svg)

## 後臺統計流程圖
![後臺統計流程圖](後臺統計流程圖.drawio.svg)

<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
  $('h2').each(function(index) {
    $(this).html((index + 1) + '. ' + $(this).html());
  });
});
</script>