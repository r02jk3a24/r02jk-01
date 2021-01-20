<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>報告一覧</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gamen9.css">
</head>
<body>
	<%
		List<String[]> resultList = (List<String[]>) request.getAttribute("resultList");
		int count = 0;
		int loop =0;
	%>

	<a href="#" class="btn-flat-simple"><i class="fa fa-caret-right"></i>戻る</a>
	<div class="UserName" align="right">UserName</div>
	<!--ログインのユーザー名を取得し表示する  -->
	<div class="Name">
		<p></p>
	</div>
	
	<h1><%=resultList.get(count)[0]%></h1><!--課題名,pnameを取得し表示する  -->
	
	<%
			if(resultList!=null){
	%>
	
	<table border="1">
		<%
			while(count < resultList.size() ) { 
		%>
		<tr>			
						<%
							if(count==0) { 
						%>
							<%=resultList.get(count)[2]%></br><!--ユーザー名を取得し表示する  -->
						<%
							}else if(resultList.get(count)[2]!=resultList.get(count-1)[2]){
						%>
							<%=resultList.get(count)[2]%></br>
						<%
							}
						%>
						
						
						<%
							if(count==0) { 
						%>
							<%=resultList.get(count)[3]%></br><!--ユーザー名を取得し表示する  -->
						<%
							}else if(resultList.get(count)[3]!=resultList.get(count-1)[3]){
						%>
							<%=resultList.get(count)[3]%></br>
						<%
							}
						%>
						
						<%=loop=count%>
						<%
							while(resultList.get(count)[3]==resultList.get(loop)[3]){
						%>
						
							<%
								if(resultList.get(count)[1] != null) { 
							%>
							報告あり
							<%=resultList.get(count)[1]%><!--repodate(報告日時)-->
							<form action="./Gamen10" method="post">
							<input type="hidden" name="name" value=Name> 
							<input　type="hidden" name="Wname" value="<%=%>"> 
							<input　type="hidden" name="Tname" value="<%=%>"> 
							<input　type="submit" value="詳細へ" class="btn-square">
							</form>
							<%
								}
							%>
						<%
							}
						%>
				
				<!--アレイリストの作業名を取得し表示する,報告のボタンを押下すると押下した作業の報告画面への遷移を行う  -->
		</tr>
		<%
			}
		%>
	</table>
	<%
			}else{
	%>
		<h1>当該課題の報告は現在上がっていません</h1>
	<%
			}
	%>
</body>
</html>