# 架構圖

## 總架構圖
![總架構圖](總架構圖.drawio.svg)

<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
  $('h2').each(function(index) {
    $(this).html((index + 1) + '. ' + $(this).html());
  });
});
</script>