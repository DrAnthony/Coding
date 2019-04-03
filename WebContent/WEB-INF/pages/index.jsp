<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<form action="/Web_test/test.jsp" method="get" name="form">
<input name="Submit" type="submit" class="btn_grey" id="submit" value="跳转">
</form>
</body>
</html>