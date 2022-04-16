<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../incloud/head-css.jsp" />
<title>總課程統計清單</title>
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" ></script>
<script src="/SpecialTopic/js/jquery.table2excel.js"></script>
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
</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
			<div style="border-bottom:3px black solid; margin:20px">
			<h1 style="text-align:center;"><font color="success">學員各項統計清單</font></h1>
			</div>
	<table class="table2excel width table table-striped">
		<thead>
			<tr>
				<td align="center" style="border-bottom:5px solid #000">課程ID</td>
				<td align="center" style="border-bottom:5px solid #000">多數性別</td>
				<td align="center" style="border-bottom:5px solid #000">平均年齡</td>
				<td align="center" style="border-bottom:5px solid #000">最多職業</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="jo" items="${mostjobMap}" varStatus="state">
				<tr>
					<td align="center"><c:out value="${jo.key}" /></td>				
					<td align="center"><c:out value="${mostgenderList[state.count-1]}" /></td>				
					<td align="center"><c:out value="${avgAgeList[state.count-1].get('avgage')}歲" /></td>				
					<td align="center"><c:out value="${jo.value}" /></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input class="btn btn-success" class="btn" type="button"  value="點選匯出excel">
	<div id="container" style="height: 400px"></div>
	<script type="text/javascript">
            
                $(".btn").click(function(){
                    $(".table2excel").table2excel({
                        // 不被匯出的表格行的CSS class類
                        exclude: ".noExl",
                        // 匯出的Excel文件的名稱
                        name: "Excel Document Name",
                        // Excel檔案的名稱
                        filename: "test",
                        //檔案字尾名
                        fileext: ".xls",
                        //是否排除匯出圖片
                        exclude_img: false,
                        //是否排除匯出超連結
                        exclude_links: false,
                        //是否排除匯出輸入框中的內容
                        exclude_inputs: false
                    });
                }); 
           
        </script>
	</div>
		</div>
	</div>
	<jsp:include page="../incloud/footer-section.jsp" />

				<div id="preloader"></div>
				<a href="#"
					class="back-to-top d-flex align-items-center justify-content-center"><i
					class="bi bi-arrow-up-short"></i></a>

				<!-- Templete JS -->
				<jsp:include page="../incloud/body-js.jsp" />

</body>
</html>