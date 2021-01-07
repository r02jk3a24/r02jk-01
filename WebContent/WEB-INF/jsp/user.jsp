<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名登録</title>
</head>
<body>
<%String user_mail = (String)request.getSession().getAttribute("email"); %>



<form method="post" action="./USER">

<H1>登録したいユーザー名を入力して下さい</H1>

ユーザー名</br><input type="text" name="name" />
<input type="hidden" name="email" value=<%=user_mail%> />

</br></br><input type="submit" value="登録">

</form>


</body>
</html>