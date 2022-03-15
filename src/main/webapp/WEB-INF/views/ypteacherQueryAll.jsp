<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YPteacherAll</title>
<link rel="stylesheet" href="/css/ordersystem.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">


    
               
      			if(data==null){
            	   $('table').prepend("<tr><td colspan='2'>暫無資料</td></tr>");
               }else{
            	   var table = $('#showproduct'); 
            	   table.append("<tr id='ptitle'><th>ID</th><th>Product Name</th><th>Product Price</th><th>Date</th><th>Note</th></tr>");

            	  
            	   $.each(data, function(i,n){
            		   var tr = "<tr align='center'>" + "<td>" + n.id + "</td>" +
            		            "<td>" + n.pname + "</td>" + "<td>" + n.price + "</td>" +
            		            "<td>" + n.pdate + "</td>" + "<td>" + n.note + "</td>" +
            		            "</tr>";
            		   table.append(tr);
                   });           	   
               }

    }


</script>
</head>
<body>
	<div id="productListTitle">YPteacherAll</div>
	<table id="showproduct" border="1"></table>	
</body>
</html>