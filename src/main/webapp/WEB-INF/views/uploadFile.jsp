<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UploadFile</title>
</head>
<body>
<form action="uploadphoto" method="post" enctype="multipart/form-data">
   Please Select Image to Upload:<br/>
   <input type="file" name="myPhoto"/><br/>
   <button type="submit" value="upload">Upload</button>
</form>
</body>
</html>