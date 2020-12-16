<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録項目確認</title>
</head>
<body>


<% 
String tname = (String)request.getSession().getAttribute("tname"); 
String wname = (String)request.getSession().getAttribute("wname");
List<String[]> workmember = (List<String[]>)request.getSession().getAttribute("workmember");
%>
<h2>課題名　<%=tname %></h2>

<h2>作業名　<%=wname %></h2>

<h3>参加メンバー</h3>
<table>
	<tr>
<%
	for(int i=0;i<workmember.size();i++){
		String[] member = workmember.get(i);
%>
	<td><%=member[1] %> </td>
<%
	} 
%>
	</tr>
</table>
<%
int itemno = (int)request.getSession().getAttribute("itemno");
String[] iname = (String[])request.getSession().getAttribute("iname");
String[] contents = (String[])request.getSession().getAttribute("contents");
String[] choiceno = (String[])request.getSession().getAttribute("choiceno");
String[] format = (String[])request.getSession().getAttribute("format");
int count = 0;
%>

<form method="post" action="./sk5_5">

<% 
for(int i=0;i<itemno;i++){ 
%>

	
	<table>
	<tr>
		<th>項目<%=i+1 %>.<%=iname[i] %></th>
		<td>
<%
	for(int j=0;j<Integer.parseInt(choiceno[i]);j++){
		
		if(Integer.parseInt(format[i])==1){
%>
			<input type="radio" name="radio<%=i %>" value="<%= j%>"><%=contents[count]%>
<%
			count+=1;
		}else if(Integer.parseInt(format[i])==2){
%>
			<input type="checkbox" name="checkbox<%=i %>" value="<%= j%>"><%=contents[count]%>
<%
			count+=1;
		}else {
%>
			<input type="text">
<%
		}
	}
%>
		</td>
	</tr>
	</table>
<%
} 
%>
	
<p>
この内容で登録しますか?
</p>
<p>
<input type="submit" value="はい">
</p>

</form>

</body>
</html>