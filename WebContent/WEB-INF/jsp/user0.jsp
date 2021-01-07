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


<form method="get" action="./USER2">

<H1><span>メールアドレスを入力して下さい</span></H1>

<h2>メールアドレス</h2></br><input type="text" name="email" />

</br></br><input type="submit" value="確認">

</form>


</body>
</html>