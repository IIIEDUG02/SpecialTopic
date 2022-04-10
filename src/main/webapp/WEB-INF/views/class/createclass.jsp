<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery -->
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h3>課程創建</h3>
	<form action="createclass" METHOD="POST" >
		<table>
			<tr>
				<td><label>標題:</label><input type="text" id="classtitle"
					name="classtitle" /></td>
			</tr>
			<tr>
				<td><label>類型:</label><input type="text" id="classtype"
					name="classtype" /></td>
			</tr>
			<tr>
				<td><label>價錢:</label><input type="text" id="classprice"
					name="classprice" /></td>
			</tr>
			<tr>
				<td><label>老師ID:</label><input type="text" id="teacherid"
					name="teacherid" /></td>
			</tr>
			<tr>
				<td><label>課程圖片:</label><input type="file" id="photopath"
					name="photopath" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="下一步" /> <input type="button"
					onclick="javascript:window.location = '/'" value="返回首頁" /></td>
			</tr>
		</table>
	</form>
	<div class="preview" id="preview">
		<div class="table">
			<div class="td"></div>
	</div>
	</div>
	<script> 
 		//預覽上傳照片(時機：選檔變更事件)
 		$("#phottpath").change(function() {
 			$("#preview .table .td").html("");//先清除前次預覽的<img>
 			previewImg(this.files);
 		});
 		function previewImg(files) {
			if (files.length == 0)
				return;
			var file = files[0];
 			var fr = new FileReader();
			//註冊：選檔被讀取完成後之事件處理器
			fr.onload = function() {
				var img = $("<img>").attr({
 					src : fr.result
 				});

 				$("#preview .table .td").html(img);
 			};
 			fr.readAsDataURL(file);
 		}
 	</script> 
</body>
</html>