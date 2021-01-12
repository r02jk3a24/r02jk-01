<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メールアドレス入力エラー</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/hello.css">
</head>
<body>


<form method="get" action="./USER2">

<H1><span>エラー！空欄です</span></H1>

<h2>メールアドレス</h2>
</br><input type="text" name="email" class="textbox"/>

</br></br><input type="submit" value="確認" class="btn-submit">

</form>


</body>
</html>