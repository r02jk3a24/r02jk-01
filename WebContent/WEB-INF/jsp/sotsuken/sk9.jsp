<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告一覧</title>
</head>
<body>
	<%	
		String pro_name = (String)request.getAttribute("pro_name");
		String pro_id = (String)request.getAttribute("pro_id");
		List<String[]> resultList = (List<String[]>) request.getSession().getAttribute("resultList");
		List<String> task_name = (List<String>) request.getAttribute("task_name");
	%>
	<h1><%=pro_name%></h1>
	<%
		if(resultList.size()==0){
	%>
		<h1>当該課題の報告は現在上がっていません</h1>
	<%
		}else{
	%>
		
		
		<form action="./sk10" method="post">
	<% 
		int count = 0;
		int index = 0;
		label:for(int i=0;i<resultList.size();i++){
			for(int c=0;c<task_name.size();c++){
			if(!resultList.get(index)[1].equals(task_name.get(count))){
	%>
				<h2><%= task_name.get(count)%></h2>
				<h3>この作業の報告は現在上がっていません</h3>
	<%
				if(count<task_name.size()-1){
					count++;
				}
			}else{
				if(count<task_name.size()-1){
					count++;
				}
				break;
			}
			}
	%>
			<h2><%= resultList.get(index)[1]%></h2>
			<table>
	<% 		
			for(int j=0;j<resultList.size();j++){
				String[] repo = resultList.get(index);
	%>
				<tr><td><%=repo[3] %></td><td><%=repo[4] %></td>
				<td><button type="submit" name="repode<%=i %>" value="<%= repo[4]%>">詳細へ</button></td></tr>
	<%
				
				if(index+1<resultList.size()){
					index++;
					if(!repo[0].equals(resultList.get(index)[0])){
						break;
					}
				}else{
					break label;
				}
			}
			
	%>
			</table>
	<%
		}
	%>
		
		<table border="1">
			
		</table>
		</form>
	<%
		}
	%>
</body>
</html>