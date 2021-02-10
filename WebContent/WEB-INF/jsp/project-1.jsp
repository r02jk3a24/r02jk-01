<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>参加課題一覧</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/project1.css">
</head>
<body>
<div class="mainlay">
<h1>参加課題一覧</h1>
<%String user_name = (String)request.getSession().getAttribute("user_name"); %>
<p class="name">ユーザー名：<%=user_name %></p>

<form action="./Sotuken3">
<p><button type="submit" value="newtask" class="newtask">新規課題登録</button></p>
</form>

<form method="post" action="./sk5_1">
<% 	List<String[]> taskList = (List<String[]>)request.getSession().getAttribute("taskList");
	if(taskList.size()==0){%>
		<div>課題はまだありません</div>
<%	}else{ %>
		<table>
		<tr>
			<td>課題名</td>
		</tr>
<%	
	for(int i=0;i<taskList.size();i++) {
		String[] task = taskList.get(i);
		String dis = "";
		if(task[2].equals("0")){
			dis = "disabled";
		}
%>
	<tr>
		<td><%=task[1] %></td>
		<td><button type="submit"name="work<%=task[0] %>" value="<%=task[0] %>" <%=dis %> class="Tbutton">作業登録</button> 
		<button type="submit"name="rep<%=task[0] %>" value="<%=task[0] %>" class="Tbutton">報告一覧</button> 
		<button type="submit"name="list<%=task[0] %>" value="<%=task[0] %>" class="Tbutton">参加作業一覧</button></td>
	</tr>
<% }
}%>	


</table>

</form>
</div>

</body>
</html>