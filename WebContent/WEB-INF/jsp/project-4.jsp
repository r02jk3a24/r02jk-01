<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー登録画面</title>
</head>
<body>
<p>4.メンバー登録画面</p>
<form method="post" action="./sk5_1">
<% 
int itemno = (int)request.getSession().getAttribute("member");
for(int i=1; i<=itemno; i++){
%>	
<h3>メンバー名.<%=i %></h3>
<input type="Text" name="Mname<%= i%>">
<%
    }
%>
<p><input type="submit" value="登録"></p>
</form>
</body>
</html>