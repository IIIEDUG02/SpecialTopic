<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>show demo</title>
</head>
<body>
  <!-- add new item Dynamically in the show block -->
  <form action="uploadphoto" method="post" enctype="multipart/form-data">  
  <div id="showBlock">
  <button type="submit" value="upload">Upload</button>
  </div>
  </form>
  <!-- click the button to add new item -->
  <input type="button" id="btn" value="addItem" />
  
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
  //set the default value
  var txtId = 1;
  
  //add input block in showBlock
  $("#btn").click(function () {
      $("#showBlock").append('<div id="div' + txtId + '"><input type="file" name="myPhoto" /> <input type="button" value="del" onclick="deltxt('+txtId+')"></div>');
      txtId++;
  });

  //remove div
  function deltxt(id) {
      $("#div"+id).remove();
  }
</script> 
</body>
</html>