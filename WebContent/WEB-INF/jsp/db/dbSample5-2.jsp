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
	<th>pro_id</th>
	<td>
	<%= ss[0] %>
	</td>
	<th>task_id</th>
	<td>
	<%= ss[1] %>
	</td>
	<th>task_name</th>
	<td>
	<%= ss[2] %>
	</td>
	<th>task_partno</th>
	<td>
	<%= ss[3] %>
	</td>
	<th>item_no</th>
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