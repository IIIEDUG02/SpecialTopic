<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>課程內容編輯</title>
<script type="text/javascript" src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
</head>
<body>
	<!-- 頁首 -->
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container"></div>
	</div>


	<form action="Action" method="POST">
		<table>
			<tbody>
				<tr>
					<td></td>
					<td><input type="hidden" name="cid"
						value="${classBean.getCid()}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" name="uid"
						value="${classBean.getUid()}" /></td>
				</tr>
				<tr>
					<td>課程種類</td>
					<td><input name="classType"
						value="${classBean.getClassType()}" /></td>
				</tr>
				<tr>
					<td>圖片</td>
					<td></td>
				</tr>
				<tr>
					<td>價錢</td>
					<td><input name="price" value="${classBean.getPrice()}" /></td>
				</tr>
				<tr>
					<td>標題</td>
					<td><input name="title" value="${classBean.getTitle()}" /></td>
				</tr>
				<tr>
					<td>說明</td>
					<td><input name="descript"
						value="${classBean.getClassDetailsBean().getDescript()}" /></td>
				</tr>
				<tr>
					<td>課程目標</td>
					<td><input name="goal" value="${classBean.getClassDetailsBean().getGoal()}" /></td>
				</tr>
				<tr>
					<td>時間長度(分鐘)</td>
					<td><input name="length_min"
						value="${classBean.getClassDetailsBean().getLength_min()}" /></td>
				</tr>
				<tr>
					<td>影片網址</td>
					<td><input name="video"
						value="${classBean.getClassDetailsBean().getVideo()}" /></td>
				</tr>
				<tr>
					<td>所需工具</td>
					<td><input name="needed_tool"
						value="${classBean.getClassDetailsBean().getNeeded_tool()}" /></td>
				</tr>
				<tr>
					<td>getStu_required</td>
					<td><input name="stu_required"
						value="${classBean.getClassDetailsBean().getStu_required()}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="確定" /></td>
				</tr>
			</tbody>
			<tfoot>
			</tfoot>
		</table>
	</form>

	<!-- 頁尾 -->
	<jsp:include page="../incloud/footer-section.jsp" />

	<!-- Templete JS -->
	<jsp:include page="../incloud/body-js.jsp" />
</body>
</html>