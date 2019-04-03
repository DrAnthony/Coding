<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎进入图片信息管理系统</h1><br>
<form action="register.do" method="post" name="t_form">
       用户名：<input name="user_name" type="text" id="user_name" size="16" ><br>
       密码：<input name="user_pwd" type="password" id="user_pwd" size="16" ><br>
       性别：男<input name="user_gender" type="radio" class="noborder" value="1" checked><br>
       女<input name="user_gender" type="radio" class="noborder" value="0"><br>
    E-mail:<input name="user_email" type="text" size="16"><br>
       年龄：<input name="user_age" type="text" size="6" ><br>
    <input name="Submit" type="submit" class="btn_grey" id="button" value="注册"><br>
</form>
<a href="default.jsp">登录账号</a>
</body>
</html>