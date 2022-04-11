<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../incloud/head-css.jsp" />
<title>Success</title>
<link rel="stylesheet" href="/css/ordersystem.css">
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" ></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://cdn.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script src="/SpecialTopic/js/jquery.table2excel.js"></script>
<style>
.position_fixed {
	position: fixed;
}

.height100 {
	height: 100px;
}
</style>
</head>
<body>
	<jsp:include page="../incloud/header-section.jsp" />
	<div class="height100"></div>
	<div class="container">
		<div class="row">
			<div class="col min-vh-100">
	<table class="table2excel">
		<thead>
			<tr>
				<td>資料成功存入:</td>
				<td>課程ID:</td>
				<td>學員職業:</td>
				<td>該職業數量:</td>
				<td>該職業占比(%):</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="th" items="${jobPercentList}">
				<tr>
					<td ><c:out value="" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.get('cid')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('job')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="共${th.get('jobcount')}筆" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('ratio')} %" /></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input class="btn" type="button"  value="點選匯出excel">
	<div id="container" style="height: 400px"></div>
	<script>

	Highcharts.chart('container',{
			  chart: {
			    type: 'pie',
			    options3d: {
			      enabled: true,
			      alpha: 45,
			      beta: 0
			    }
			  },
			  title: {
			    text: '學員職業分布比例'
			  },
			  tooltip: {
			    pointFormat: '{series.name}: <b>{point.mycustomLabel}</b>'
			    // pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			  },
			  plotOptions: {
			    pie: {
			      allowPointSelect: true,
			      cursor: 'pointer',
			      depth: 35,
			      dataLabels: {
			        enabled: true,
			        format: '{point.name}'
			      }
			    }
			  },
			  series: [{
			    type: 'pie',
			    name: 'Browser share',
			    data: [
// 			      [`${genderList[0]["gender"]}`,   ${genderList[0]["ratio"]}],
// 			      [`${genderList[0]["gender"]}`,   ${genderList[0]["ratio"]}],
			      
			      
			    	<c:set var="count" scope="page" value="${0}"/>
			    	<c:forEach var="job" items="${jobPercentList}">
				    	<c:choose>
					        <c:when test="${count==0}">
					        {
						        name: `${job.get("job")} `+`(${job.get("ratio")}%)`,
						        y: ${job.get("ratio")},
						        sliced: true,
						        selected: true,
						        mycustomLabel: 'ithelp-ithelp-ithelp-ithelp'
						      },
					    	 <c:set var="count" scope="page" value="${count + 1}"/>
					    	 </c:when>
					    	    
					    	 <c:otherwise>
					    	 	[`${job.get("job")} `+`(${job.get("ratio")}%)`,   ${job.get("ratio")}],
					    	 </c:otherwise>
				    	</c:choose>
			    	</c:forEach>	
			    ]
			  }]
			});
	</script>
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