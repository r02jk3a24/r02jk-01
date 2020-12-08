<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>作業登録</title>
</head>
<body>
<form method="post" action="./sk5_2">

<h1>作業登録</h1>

<table>
<tr>
	<td>作業名</td><td><input type="text" name="wname"></td>
</tr>
<tr>
	<td>参加メンバー</td>
	<td>
	<% for(int i=1;i<=3;i++){%>
		<input type="checkbox" name="membervalues" value="<%= i%>">メンバー名A<br/>
	<% }%>
	</td>
<tr>
	<td>項目数</td>
	<td><input type="text" name="itemno"></td>
</tr>
</table>



<input type="submit" value="次へ">

</form>

</body>
</html>