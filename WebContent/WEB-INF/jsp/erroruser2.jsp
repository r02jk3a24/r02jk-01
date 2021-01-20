<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名登録エラー</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/hello.css">
</head>
<body>
<%String user_mail = (String)request.getSession().getAttribute("email"); %>

<h1><span>エラー！ユーザー名が空欄です！</span></h1>

<form method="post" action="./USER">

<h2>ユーザー名</h2>

</br><input type="text" name="name" class="textbox"/>
<input type="hidden" name="email" value="<%=user_mail%>" />

</br></br><input type="submit" value="登録" class="btn-submit">

</form>


</body>
</html>