<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー登録画面</title>
</head>
<body>
<p>4.メンバー登録画面</p>
<form method="post" action="./Sotuken4K">
<p>↓リーダー</p>
<% 
int pron = (int)request.getSession().getAttribute("pro_partno");
for(int i=1; i<=pron; i++){
%>	

<h3>メンバー名.<%=i %></h3>
<input type="Text" name="uname" value="<%= i%>">



<%
    }
%>
<input type=hidden name="PRON" value=<%= pron%>>
<p><input type="submit" value="登録"></p>
</form>
</body>
</html>