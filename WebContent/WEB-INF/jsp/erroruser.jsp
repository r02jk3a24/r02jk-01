<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名登録</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/hello.css">
</head>
<body>
<%String user_mail = (String)request.getSession().getAttribute("user_mail"); %>

<h1><span>そのユーザー名は、既に使用されています！</h1>

<form method="post" action="./USER">

ユーザー名</br><input type="text" name="name" />
<input type="hidden" name="mail" value=<%=user_mail%> />

</br></br><input type="submit" value="登録">

</form>


</body>
</html>