<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
</head>
<body>

	<div>
		<a>Start @: <span id="spanStart"></span></a>
	</div>

	<div>
		<a>pause @: <span id="spanPause"></span></a>
	</div>

	<button id="btnStart">開始</button>
	<button id="btnPause">暫停</button>
	<script>
		var startTime;
		var pauseTime;
		$('button#btnStart').click(function() {
			startTime = new Date();
			$('span#spanStart').html(startTime);
			alert(startTime);
		})
		$('button#btnPause').click(function() {
			pauseTime = new Date();
			$('span#spanPause').html(pauseTime);
			alert(pauseTime);
			alert((pauseTime - startTime) / 1000);
		})

		
	</script>
</body>
</html>