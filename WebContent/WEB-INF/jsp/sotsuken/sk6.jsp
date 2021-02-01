<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>参加作業一覧</title>
</head>
<body>
<%String user_name = (String)request.getSession().getAttribute("user_name"); %>
<p>ユーザー名：<%=user_name %></p>

<form method="post" action="./sk8">
<% 	List<String[]> ptaskList = (List<String[]>)request.getSession().getAttribute("ptaskList");
	if(ptaskList.size()==0){%>
		<div>参加中の作業はありません</div>
<%	}else{ %>
		<%=ptaskList.get(0)[1] %>
		<table>
		<tr>
			<td>作業名</td>
		</tr>
<%	
	for(int i=0;i<ptaskList.size();i++) {
		String[] task = ptaskList.get(i);
%>
	<tr>
		<td><%=task[3] %></td>
		<td><button type="submit"name="repo<%=i %>" value="<%=task %>">報告</button></td>
	</tr>
<% }
}%>	


</table>

</form>


</body>
</html>