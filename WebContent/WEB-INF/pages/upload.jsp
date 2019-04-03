<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="pic/upload" enctype="multipart/form-data" method="post">
 <table>
   <tr>
      <td>选择文件:</td><td><input name="file" type="file" id="file"></td>
   </tr>
   <tr><td>选择类别:</td></tr>
   <tr>
      <td><input name="pic_tag" type="radio" id="pic_tag" value="1" checked>人物</td>
      <td><input name="pic_tag" type="radio" id="pic_tag" value="2" >游戏</td>
      <td><input name="pic_tag" type="radio" id="pic_tag" value="3" >风景</td>
   </tr>
 </table>
 请输入图片描述:<br>
 <input name="pic_desc" type="text" id="pic_desc"><br>
 <input type="submit" class="btn_grey" value="上传">
 </form>
</body>
</html>