<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎进入图片信息管理系统</h1><br>
<form action="login.do" method="post" name="form1">
  用户名：<input name="user_name" type="text" id="user_name"><br>
 密码：<input name="user_pwd" type="password" id="user_pwd"><br>
 <input name="Submit" type="submit" class="btn_grey" value="登录"><br>
</form>
<a href="register.jsp">注册账号</a>
</body>
</html>