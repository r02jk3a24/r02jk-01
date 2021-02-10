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
String[] iname = (String[])request.getSession().getAttribute("iname");
int nitem = (int)request.getSession().getAttribute("itemno");
String[] choiceno = (String[])request.getSession().getAttribute("choiceno");
String[] format = (String[])request.getSession().getAttribute("format");
for(int i=1; i<=nitem; i++){ 
%>
	<h3>項目<%= i%>.<%=iname[i-1] %></h3>
	
	<p>
<%		if(Integer.parseInt(format[i-1])==1){%>
			ラジオボタン形式
<%		}else if(Integer.parseInt(format[i-1])==2){%>
			チェックボックス形式
<%		}else{ %>	
			テキスト形式
<%		} %>
	</p>
	<table>
<% 
	if(Integer.parseInt(format[i-1])==1||Integer.parseInt(format[i-1])==2){
		for(int j=1; j<=Integer.parseInt(choiceno[i-1]); j++){
%>
			<tr>
				<td>
					選択肢<%= j%>内容</td><td><input type="text" name="contents">
				</td>
			</tr>
<%		} %>
<% 	}%>	
	
	</table>
<%} %>


<input type="submit" value="次へ">

</form>

</body>
</html>