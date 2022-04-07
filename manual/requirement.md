# 功能需求
## 前台
### 首頁
- 首頁顯示公司名稱與logo。在任何網頁，點擊公司名稱與logo回到首頁。
- 已登入時顯示客戶姓名與購物車文字或圖示及功能選單。
- 顯示月度、年度熱門課程。

### 課程瀏覽
- 進入購買課程頁面
- 
### 播放課程
- 影片播放
- 選取章節更換影片

### 文章分享
“Knowledge is power.”
給學員們分享各種學習相關的文章，除了自己報名的課程以外，也能多方學習補充新知、精進自己、增加競爭力。

### 留言板
給使用者們留言詢問問題、留言互動等等。

### etc...


## 後台

### 個人課程瀏覽
- 進入播放課程頁面

### 編輯所有課程
- 顯示所有課程
- 新增課程
- 下架課程
- 編輯課程

### 購物車與結帳
- 使用第三方支付平台。
- 已購買的課程，無法點擊購買鈕，並提示已購買。
- 顯示購物車當前數量。
- 結帳畫面需完整顯示課程清單（包含課程名稱及價格）。
- 完成第三方支付平台流程後，返回個人課程瀏覽頁面。

### 帳戶
- 會員註冊。
- 修改會員資料。

### etc...

<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
  $('h2').each(function(index) {
    $(this).html((index + 1) + '. ' + $(this).html());
  });
});
</script>