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
<script src="https://code.highcharts.com/highcharts.js"></script>
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
	<table>
		<thead>
			<tr>
				<td>資料成功存入:</td>
				<td>課程ID:</td>
				<td>年齡:</td>
				<td>該年齡數量:</td>
				<td>該年齡占比:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="th" items="${agePercentList}">
				<tr>
					<td ><c:out value="" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.get('cid')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('age')}歲" /></td>				
					<td style="border-top:1px solid #000"><c:out value="共${th.get('agecount')}筆" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('ratio')} %" /></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
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
			    text: '學員年齡分布比例'
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
			    	<c:forEach var="age" items="${agePercentList}">
				    	<c:choose>
					        <c:when test="${count==0}">
					        {
						        name: `${age.get("age")}歲 `+`(${age.get("ratio")}%)`,
						        y: ${age.get("ratio")},
						        sliced: true,
						        selected: true,
						        mycustomLabel: 'ithelp-ithelp-ithelp-ithelp'
						      },
					    	 <c:set var="count" scope="page" value="${count + 1}"/>
					    	 </c:when>
					    	    
					    	 <c:otherwise>
					    	 	[`${age.get("age")}歲 `+`(${age.get("ratio")}%)`,   ${age.get("ratio")}],
					    	 </c:otherwise>
				    	</c:choose>
			    	</c:forEach>	
			    ]
			  }]
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