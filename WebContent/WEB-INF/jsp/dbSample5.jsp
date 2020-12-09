<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>r02jk-01</title>
</head>
<body>
<%
			List<String[]> resultList = 
			(List<String[]>)request.getAttribute("resultList");
%>

<table border=1>
<%
for(String[] ss : resultList){
	%>
	<tr>
	<th>
	<%= ss[0] %>
	</th>
	<td>
	<%= ss[1] %>
	</td>
	<td>
	<%= ss[2] %>
	</td>
	<td>
	<%= ss[3] %>
	</td>
	<td>
	<%= ss[4] %>
	</td>
	
	</tr>
	<%
}
%>
</table>
</body>
</html>