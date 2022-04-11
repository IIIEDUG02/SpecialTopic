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
	<form action="createclass" METHOD="POST" enctype="multipart/form-data">
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
					<td><img id="preview_img" src="#" alt="123" style="width: 100px;height: 100px"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="下一步" /> <input type="button"
					onclick="javascript:window.location = '/'" value="返回首頁" /></td>
			</tr>
		</table>
	</form>
<script>

$("#photopath").change(function(){
  readURL(this);
});
function readURL(input){
  if(input.files && input.files[0]){
    var reader = new FileReader();
    reader.onload = function (e) {
       $("#preview_img").attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>
</body>
</html>