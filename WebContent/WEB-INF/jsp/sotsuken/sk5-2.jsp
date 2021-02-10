<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>項目登録</title>
</head>
<body>
<h1>項目登録</h1>
<p>※テキスト形式を選択した場合選択肢数は1となります。</p>
<form method="post" action="./sk5_3">
<% 
int itemno = (int)request.getSession().getAttribute("itemno");
for(int i=1; i<=itemno; i++){
%>	
<h3>項目<%=i %></h3>

<table>
<tr>
	<td>項目名</td><td><input type="text" name="iname"></td>
</tr>
<tr>
	<td>形式選択</td>
	<td>
		<input type="radio" name="format<%=i %>" value="1">ラジオボタン
		<input type="radio" name="format<%=i %>" value="2">チェックボックス
		<input type="radio" name="format<%=i %>" value="3">テキスト
	</td>
<tr>
	<td>選択肢数</td>
	<td><input type="text" name="choiceno"></td>
</tr>
</table>

<%} %>

<input type="submit" value="次へ">

</form>

</body>
</html>