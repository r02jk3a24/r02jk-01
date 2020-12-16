<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gamen9.css">
</head>
<body>
<%
	List<String[]> resultList = (List<String[]>)request.getAttribute("resultList");
%>
 
<a href="#" class="btn-flat-simple"><i class="fa fa-caret-right"></i>戻る</a>  <div class="UserName" align="right">UserName</div><!--ログインのユーザー名を取得し表示する  -->
<div class ="Name"><p><%=resultList.get(3)%></p></div><!--課題名を取得し表示する  -->
<table border="1">
<%
	for(String[] s:resultList){ 	//ここに変数仕込む、全体の作業数を取得しそれを利用
%>
		
    <form action="./Gamen6" method="post">
    
    <div class="box30">
    <div class="box-title"><%=s[0] %></div><!--アレイリストの課題名を取得し表示する  -->
    <p><%=s[1] %></p>
    <p><%=s[2] %></p>
    <input type="hidden" name="name" value=Name>
    <input type="hidden" name="Wname" value=<%=s[0] %>>
    <input type="hidden" name="Tname" value=<%=s[1] %>>
    <input type="submit" value="詳細へ"	class="btn-square">
     </div>
    </form> <!--アレイリストの作業名を取得し表示する,報告のボタンを押下すると押下した作業の報告画面への遷移を行う  -->
		
		
<%
	} 
%>
</table>

</body>
</html>