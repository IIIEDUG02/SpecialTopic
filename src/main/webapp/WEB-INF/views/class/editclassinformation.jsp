<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<style>
body {
	font-family: "PingFang TC", 微軟正黑體, sans-serif;
	font-size: 16px;
	color: rgba(0, 0, 0, 0.65);
	margin: 10px;
	padding: 0px;
	min-height: 100vh;
}

div#title {
	
}

label {
	margin-top: 15px;
	margin-bottom: 10px;
}

.btn {
	margin-top: 20px;
}

.input-wrap {
	min-width: 380px;
	margin: 10px;
	background-color: #F0FFF0;
	margin-bottom: 60px;
	padding: 15px 30px 45px;
	border-radius: 50px;
}

img {
	margin-top: 20px;
}
</style>

<meta charset="UTF-8">
<title>編輯課程內容</title>
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />

</head>
<body>
	<!-- 頁首 -->
	<jsp:include page="../incloud/header-section.jsp" />

		<div class="height100"></div>

	<div class="container ">
		<div id="title" class="shawdow row " style="text-align: center">
			<h3>
				<strong>編輯課程內容</strong>
			</h3>
		</div>
		<div class="shawdow col input-wrap">

			<form action="/SpecialTopic/updateclass/${classBean.getCid()}"
				METHOD="POST" enctype="multipart/form-data">
				<div class="form-group">
					<label for="classtitle">課程標題</label> <input type="text"
						class="form-control" id="classtitle" name="classtitle"
						placeholder="請輸入文字" required="required"
						value="${classBean.getTitle()}">
				</div>
				<div class="form-group">
					<label for="classtype">課程類型</label> <input type="text"
						class="form-control" id="classtype" name="classtype"
						placeholder="請輸入文字" required="required"
						value="${classBean.getClassType()}">
				</div>
				<div class="form-group">
					<label for="classprice">價錢</label> <input type="text"
						class="form-control" id="classprice" name="classprice"
						placeholder="請輸入文字" required="required"
						value="${classBean.getPrice()}">
				</div>
				<div class="form-group">
					<label for="teacherid">老師ID</label> <input type="text"
						class="form-control" id="uid" name="uid"
						placeholder="請輸入ID" required="required"
						value="${classBean.getUid()}">
				</div>
				<div
					class="form-group row align-items-center justify-content-center">
					<div class="col">
						<label for="photopath">課程圖片</label> <input type="file"
							class="form-control" id="photopath" name="photopath"
							placeholder="課程圖片">
					</div>
					<div class="col">
						<img id="preview_classphoto" src="https://iiiedug02.nilm.in${classBean.getPhoto()}" alt="預覽圖片"
							style="width: 260px; height: 200px; border: solid" />
					</div>
				</div>



				<div class="form-group col">
					<label for="descript">課程描述</label>
					<textarea class="form-control" id="descript" name="descript"
						placeholder="請輸入文字" required>${classBean.getClassDetailsBean().getDescript()}</textarea>
				</div>
				<div class="form-group">
					<label for="needed_tool">自備工具</label>
					<textarea class="form-control" id="needed_tool" name="needed_tool"
						placeholder="請輸入文字" required>${classBean.getClassDetailsBean().getNeeded_tool()}</textarea>
				</div>
				<div class="form-group">
					<label for="stu_required">學生要求</label>
					<textarea class="form-control" id="stu_required"
						name="stu_required" placeholder="請輸入文字" required>${classBean.getClassDetailsBean().getStu_required()}</textarea>
				</div>
				<div class="form-group">
					<label for="length_min">影片長度(分鐘)</label> <input type="text"
						class="form-control" id="length_min" name="length_min"
						placeholder="請輸入ID"
						value="${classBean.getClassDetailsBean().getLength_min()}" required>
				</div>
				<div class="form-group">
					<label for="goal">目標</label>
					<textarea class="form-control" id="goal" name="goal"
						placeholder="請輸入ID" required>${classBean.getClassDetailsBean().getGoal()}</textarea>
				</div>
				<div class="form-group">
					<label for="video">影片連結</label> <input type="text"
						class="form-control" id="video" name="video" placeholder="請輸入ID"
						value="${classBean.getClassDetailsBean().getVideo()}">
				</div>
				<div style="text-align: center">
					<input type="submit" class="btn btn-primary" value="完成" />
				</div>
			</form>

		</div>


	</div>





	<script>
		$("#photopath").change(function() {
			$("#preview_classphoto").attr('src', "");
			readURL(this);
		});
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview_classphoto").attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>

	<!-- 頁尾 -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
</body>
</html>