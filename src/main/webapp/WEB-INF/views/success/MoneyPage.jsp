<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../incloud/head-css.jsp" />
<title>Success</title>
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" ></script>
<script src="https://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
<script src="https://cdn.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script src="https://cdn.highcharts.com.cn/highcharts/modules/oldie.js"></script>
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
	<div class="table2excel">
	<table style= "display:inline;" class="width table table-striped">
		<thead>
			<tr>
				<td>2021年</td>
				<td>銷售額</td>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-01" /></td>				
					<td style="border-top:1px solid #000"><c:out value="2340 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-02" /></td>				
					<td style="border-top:1px solid #000"><c:out value="12340 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-03" /></td>				
					<td style="border-top:1px solid #000"><c:out value="20340 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-04" /></td>				
					<td style="border-top:1px solid #000"><c:out value="23400 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-05" /></td>				
					<td style="border-top:1px solid #000"><c:out value="23330 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-06" /></td>				
					<td style="border-top:1px solid #000"><c:out value="30500 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-07" /></td>				
					<td style="border-top:1px solid #000"><c:out value="14030 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-08" /></td>				
					<td style="border-top:1px solid #000"><c:out value="34550 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-09" /></td>				
					<td style="border-top:1px solid #000"><c:out value="31020 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-10" /></td>				
					<td style="border-top:1px solid #000"><c:out value="25600 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-11" /></td>				
					<td style="border-top:1px solid #000"><c:out value="20320 元" /></td>							
				</tr>
				<tr>
					<td style="border-top:1px solid #000"><c:out value="2021-12" /></td>				
					<td style="border-top:1px solid #000"><c:out value="14530 元" /></td>							
				</tr>
		</tbody>
	</table>
	<table style="display:inline;" class="width table table-striped">
		<thead>
			<tr>
				<td>2021 年</td>
				<td>銷售額</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="my" items="${moneyList2022}">
				<tr>
					<td style="border-top:1px solid #000"><c:out value="${my.get('date')}" /></td>				
					<td style="border-top:1px solid #000"><c:out value="${my.get('money')} 元" /></td>							
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<input class="btn btn-success" type="button" value="點選匯出excel">
	<div id="container" style="min-width:400px;height:400px"></div>
	<script>

	var chart = Highcharts.chart('container', {
	    chart: {
	        type: 'line'
	    },
	    title: {
	        text: '月銷售額'
	    },
	   
	    xAxis: {
	        categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
	    },
	    yAxis: {
	        title: {
	            text: '總額 (TWD)'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                // 开启数据标签
	                enabled: true          
	            },
	            // 关闭鼠标跟踪，对应的提示框、点击事件会失效
	            enableMouseTracking: false
	        }
	    },
	    series: [{
	        name: '2021',
	        data: [2340, 12340, 20340, 23400, 23330, 30500, 14030, 34550, 31020, 25600, 20320, 14530]
	    }, {
	        name: '2022',
	        data: [
	        	<c:forEach var="my" items="${moneyList2022}">
		    	
			    	[${my.get('money')}],
			    	 
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