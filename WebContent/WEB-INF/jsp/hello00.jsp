<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>認証完了</title>
</head>
<body>
<H1>認証できました</H1>
<form action="/auth/msredirect" method="post">
<input type="submit" value="次へ" class="btn-submit">
</form>

</body>
</html>