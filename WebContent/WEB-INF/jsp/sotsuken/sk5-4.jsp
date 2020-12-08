<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録項目確認</title>
</head>
<body>

<h2>課題名</h2>
<% 
String wname = (String)request.getSession().getAttribute("wname");

%>
<h2>作業名　<%=wname %></h2>

<h3>参加メンバー</h3>


<%
int itemno = (int)request.getSession().getAttribute("itemno");
String[] iname = (String[])request.getSession().getAttribute("iname");
String[] contents = (String[])request.getSession().getAttribute("contents");
String[] choiceno = (String[])request.getSession().getAttribute("choiceno");
String[] format = (String[])request.getSession().getAttribute("format");
%>

<form method="post" action="./sk5_5">

<% 
for(int i=0;i<itemno;i++){ 
%>
	<h3>項目<%=i+1 %>.<%=iname[i] %></h3>
<%
	for(int j=0;j<Integer.parseInt(choiceno[i]);j++){
		if(Integer.parseInt(format[i])==1){
%>
			<input type="radio" value="<%= j%>"><%=contents[j]%>
<%
		}else if(Integer.parseInt(format[i])==2){
%>
			<input type="checkbox" value="<%= j%>"><%=contents[j]%>
<%
		}else {
%>
			<%=contents[j]%><input type="text">
<%
		}
	}

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