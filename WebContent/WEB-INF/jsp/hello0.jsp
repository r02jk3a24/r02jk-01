<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>認証します</title>
</head>
<body>
<%String url = (String)request.getAttribute("url"); %>
<H1>下のボタンをクリックしてください</H1>
<a href="<%=url%>">認証用URL</a>
<form>
<input type="submit" value="認証する" formaction="<%=url %>">
</form>>

</body>
</html>