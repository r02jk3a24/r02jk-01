<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択肢内容登録</title>
</head>
<body>
<h1>選択肢内容登録</h1>
<form method="post" action="./sk5_4">
<% 

int nitem = (int)request.getSession().getAttribute("itemno");
String[] choiceno = (String[])request.getSession().getAttribute("choiceno");
String[] format = (String[])request.getSession().getAttribute("format");
for(int i=1; i<=nitem; i++){ 
%>
	<h3>項目<%= i%></h3>

	<table>
	<% 
	
	int nchoice = Integer.parseInt(choiceno[i-1]);
	for(int j=1; j<=nchoice; j++){ 
	%>
	<tr>
		<td>
<%		if(Integer.parseInt(format[i])==1||Integer.parseInt(format[i])==2){%>
			選択肢<%= j%>内容</td><td><input type="text" name="contents">
<% 		}else {%>	
			テキスト形式
<%		} %>
		</td>
	</tr>
	<%} %>
</table>
<%} %>


<input type="submit" value="次へ">

</form>

</body>
</html>