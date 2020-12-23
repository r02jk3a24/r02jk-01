<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ</title>
</head>
<body>
<%String user_id = (String)request.getSession().getAttribute("userid"); %>
<%String user_name = (String)request.getSession().getAttribute("name"); %>
<H1>ようこそ<%=user_name %>さん</H1>
<H2>あなたのIDは<%=user_id %>です</H2>

<form method="get" action="./Sotuken1">
<input type="hidden" name=userid value<%=user_id %>>
<input type="submit" value="はじめる">
</form>


</body>
</html>