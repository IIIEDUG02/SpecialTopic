<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
<link rel="stylesheet" href="/css/ordersystem.css">
<script src="https://code.highcharts.com/highcharts.js"></script>

</head>
<body>
	<table>
		<thead>
			<tr>
				<td>資料成功存入:</td>
				<td>課程ID:</td>
				<td>學員職業:</td>
				<td>該職業數量:</td>
				<td>該職業占比:</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="th" items="${jobPercentList}">
				<tr>
					<td ><c:out value="" /></td>
					<td style="border-top:1px solid #000"><c:out value="${th.get('cid')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('job')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('jobcount')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${th.get('ratio')}" /></td>				
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

</body>
</html>