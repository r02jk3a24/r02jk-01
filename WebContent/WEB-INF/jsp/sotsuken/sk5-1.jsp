<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>作業登録</title>
</head>
<body>
<form method="post" action="./sk5_2">

<h1>作業登録</h1>

<%
List<String[]> username = (List<String[]>)request.getSession().getAttribute("username");
String tname = (String)request.getSession().getAttribute("tname"); 
%>
<h3>課題名　<%=tname %></h3>

<table>
<tr>
	<td>作業名</td><td><input type="text" name="wname"></td>
</tr>
<tr>
	<td>参加メンバー</td>
	<td>
	<%	for(int i=0;i<username.size();i++){
		String[] user = username.get(i);
	%>
		<input type="checkbox" name="membervalues" value="<%= i%>"><%=user[1] %><br/>
	<% }%>
	</td>
<tr>
	<td>項目数</td>
	<td><input type="text" name="itemno"></td>
</tr>
</table>



<input type="submit" value="次へ">

</form>

</body>
</html>