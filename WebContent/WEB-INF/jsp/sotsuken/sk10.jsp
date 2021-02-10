<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告詳細</title>
</head>
<body>
<form method="post" action="./sk8_2">

<h1>報告詳細</h1>

<%	
	String pro_name = (String)request.getAttribute("pro_name");
	String task_name = (String)request.getAttribute("task_name");
	int item_no = (int)request.getAttribute("item_no");
	String user = (String)request.getAttribute("user");
	List<String> user_name = (List<String>)request.getAttribute("user_name");
	List<String[]> taskitem = (List<String[]>)request.getAttribute("taskitem");
	List<String> con_name = (List<String>)request.getAttribute("con_name");
	List<String[]> repo = (List<String[]>)request.getAttribute("repo");
%>

<h2>課題名　<%=pro_name %></h2>

<h2>作業名　<%=task_name %></h2>

<h2>報告者名　<%= user%></h2>

<h3>参加メンバー</h3>
<table>
	<tr>
<%
	for(int i=0;i<user_name.size();i++){
%>
	<td><%=user_name.get(i) %> </td>
<%
	} 
%>
	</tr>
</table>

<%
	int count = 0;
	for(int i=1;i<=item_no;i++){
		String[] item = taskitem.get(i-1);
		
%>
	<table>
	<tr>
		<th>項目<%=i %>.<%=item[3] %></th>
		<td>
<%
	for(int j=0;j<Integer.parseInt(item[2]);j++){
		String[] report = repo.get(count);
		String status = "disabled";
		if(report[2].equals("選択")){
			status="checked";
		}
		if(Integer.parseInt(item[1])==1){
			
%>
			<input type="radio" name="radio<%=i %>" value="<%= j%>" <%=status %>><%=con_name.get(count)%>
<%
			count++;
		}else if(Integer.parseInt(item[1])==2){
%>
			<input type="checkbox" name="checkbox<%=i %>" value="<%= j%>" <%=status %>><%=con_name.get(count)%>
<%
			count++;
		}else {
%>
			<input name="text<%=i %>" type="text" disabled value="<%= report[2]%>">
<%
			count++;
		}
	}
%>
		</td>
	</tr>
	</table>
<%
} 
%>

</form>

</body>
</html>