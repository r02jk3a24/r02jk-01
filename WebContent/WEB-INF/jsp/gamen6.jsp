<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>参加作業一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gamen6.css">
</head>
<body>
<%
	
	List<String[]>  list	=	(List<String[]>)request.getAttribute("resultList");
%>
 
<a href="#" class="btn-flat-simple"><i class="fa fa-caret-right"></i>戻る</a>  <div class="UserName" align="right">UserName</div><!--ログインのユーザー名を取得し表示する  -->
<div class ="Name"><h1>報告一覧</h1></div><!--メンバー名を取得し表示する  -->
<%
	for(String[] s:list){ 	//ここに変数仕込む、全体の作業数を取得しそれを利用
%>
		<div class="box30">
    	<div class="box-title"><%=s[0] %></div><!--アレイリストの課題名を取得し表示する  -->
    	<p><%=s[1] %></p>
    <form action="./Gamen10" method="post">
    <input type="hidden" name="name" value=Name>
    <input type="hidden" name="Wname" value="<%=s[0] %>">
    <input type="hidden" name="Tname" value="<%=s[1] %>">
    <input type="submit" value="報告" class="btn-square"></form> <!--アレイリストの作業名を取得し表示する,報告のボタンを押下すると押下した作業の報告画面への遷移を行う  -->
		</div>
		
<%
	} 
%>


</body>
</html>