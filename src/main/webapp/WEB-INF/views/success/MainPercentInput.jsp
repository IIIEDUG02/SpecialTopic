<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPercentInput</title>
<link href="/SpecialTopic/css/jquery-ui.css" rel="stylesheet" />
<!-- Favicons -->
<jsp:include page="../incloud/favicons.jsp" />
<!-- Head CSS -->
<jsp:include page="../incloud/head-css.jsp" />
<!-- Head js -->
<jsp:include page="../incloud/head-js.jsp" />
<!-- JavaScript -->
<script src="/SpecialTopic/js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
.width{
	width: 600px;
}

.table-striped>tbody>tr:nth-child(odd)>td, 
.table-striped>tbody>tr:nth-child(odd)>th {
   background-color: rgb(210, 244, 224   ); 
 }
 #tabs{
 margin-left: auto;
 margin-right:auto;
 }

</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
			<div>
					<table  align="center" class="width table table-striped">
						<tr>
							<td align="center" style="border-bottom:5px solid #000">課程ID</td>
							<td align="center" style="border-bottom:5px solid #000">課程名稱</td>
							<td align="center" style="border-bottom:5px solid #000">課程種類</td>
							<td align="center" style="border-bottom:5px solid #000">訂單數量</td>
						</tr>
						<c:forEach var="ca" items="${classList}">
							<tr>
								<td  id="cid" align="center"><c:out value="${ca.get('cid')}" /></td>				
								<td align="center"><c:out value="${ca.get('title')}" /></td>				
								<td align="center"><c:out value="${ca.get('class_type')}" /></td>							
								<td align="center"><c:out value="共${ca.get('count')}筆" /></td>							
							</tr>
						</c:forEach>
					</table>
			</div >			
				<div id="tabs" class="width marg-auto"  >
					<ul>
						<li><a href="#p1">學員職業統計</a></li>
						<li><a href="#p2">學員性別統計</a></li>
						<li><a href="#p3">學員年齡統計</a></li>
						<li><a href="#p4">學員總統計</a></li>
					</ul>
					<div id="p1">
						<form action="/SpecialTopic/getJobPercentbyID" method="get" id="jobform">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input  class="form-control" placeholder="請輸入ID" type="text" name="id" id="jobid" /></td>
									<td ><font id="jobmsg" color="red"></font></td>
								</tr>
							</table>
						</form>
						<input type="button" onclick="checkjob()" class="btn btn-success" value="送出" />
									<font id="joberrorID" color="red"></font>
					</div>
					<div id="p2">
						<form action="/SpecialTopic/getgenderbyID" method="get" id="genderform">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input class="form-control" placeholder="請輸入ID" type="text" name="id" id="genderid" /></td>
									<td><font id="gendermsg" color="red"></font></td>
								</tr>
							</table>
						</form>
						<input type="button" onclick="checkgender()" class="btn btn-success" value="送出" />
									<font id="gendererrorID"  color="red"></font>
					</div>
					<div id="p3">
						<form action="/SpecialTopic/getAgePercentbyID" method="get" id="ageform">
							<table>
								<tr>
									<td>課程ID:</td>
									<td><input class="form-control" placeholder="請輸入ID" type="text" name="id" id="ageid" /></td>
									<td><font id="agemsg" color="red"></font></td>
								</tr>
							</table>
						</form>
						<input type="button" onclick="checkage()" class="btn btn-success" value="送出" />
									<font id="ageerrorID" color="red"></font>
					</div>
					<div id="p4">
						<input type="button" onclick="location.href='/SpecialTopic/getAllData'" class="btn btn-success" value="查看清單" />
					</div>
				</div>
				<div class="row" id="mostjob" data-aos="zoom-in" data-aos-delay="100"></div>
			</div>
		</div>
	</div>
	<script>
	function checkgender() {
		var genderid = $('input#genderid');
		$('font#gendererrorID').html('');
		
		if (genderid.val() ==='') {
			$('font#gendermsg').html('請輸入課程ID');
			return false;
		}else{
			$('font#gendermsg').html('');
			var tdList=$("td#cid");
			var List =[];
			var cidListLength=tdList.length;
			for(let i = 0; i < cidListLength; i++){
				List.push( tdList[i].innerHTML );
				
				}
			
			if(List.indexOf($("input#genderid").val())==-1){
				$('font#gendererrorID').html('請確認課程ID存在於列表');
				return;
			}
			$('form#genderform').submit();
		}

		
		
	}
	function checkjob() {

		var jobid = $('input#jobid');
		$('font#joberrorID').html('');
		
		if (jobid.val() ==='') {
			$('font#jobmsg').html('請輸入課程ID');
			return false;
		}else{
			$('font#jobmsg').html('');
			var tdList=$("td#cid");
			var List =[];
			var cidListLength=tdList.length;
			for(let i = 0; i < cidListLength; i++){
				List.push( tdList[i].innerHTML );
				
				}
			
			if(List.indexOf($("input#jobid").val())==-1){
				$('font#joberrorID').html('請確認課程ID存在於列表');
				return;
			}
			$('form#jobform').submit();
		}

		
		
	}
	function checkage() {
		
		var ageid = $('input#ageid');
		$('font#ageerrorID').html('');
		if (ageid.val() ==='') {
			$('font#agemsg').html('請輸入課程ID');
			return false;
		}else{
			$('font#agemsg').html('');
			var tdList=$("td#cid");
			var List =[];
			var cidListLength=tdList.length;
			for(let i = 0; i < cidListLength; i++){
				List.push( tdList[i].innerHTML );
				
				}
			
			if(List.indexOf($("input#ageid").val())==-1){
				$('font#ageerrorID').html('請確認課程ID存在於列表');
				return;
			}
			$('form#ageform').submit();
		}

		
		
	}
	</script>
	<jsp:include page="../incloud/footer-section.jsp" />

				<div id="preloader"></div>
				<a href="#"
					class="back-to-top d-flex align-items-center justify-content-center"><i
					class="bi bi-arrow-up-short"></i></a>

				<!-- Templete JS -->
				<jsp:include page="../incloud/body-js.jsp" />
</body>
</html>