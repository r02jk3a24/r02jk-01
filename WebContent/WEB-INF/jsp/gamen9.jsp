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
	String mae = null;
	String WorkName = null;
	String User=null;
	%>

	<a href="#" class="btn-flat-simple"><i class="fa fa-caret-right"></i>戻る</a>
	<div class="UserName" align="right">UserName</div>
	<!--ログインのユーザー名を取得し表示する  -->
	<div class="Name">
		<p></p>
	</div>
	<!--課題名を取得し表示する  -->
	<table border="1">
		<%
			for (String[] s : resultList) { //ここに変数仕込む、全体の作業数を取得しそれを利用
		%>
		<tr>
			<%
				if (WorkName == null) {
				WorkName = s[0];
			%>

			<%
				}
			%>
			<%
				if (WorkName.equals(s[0])==false) {
			%>
				<p><%=s[0]%></p>
			<%
				WorkName = s[0];
			%>
			<%
				}
			%>


			<%
				if (mae == null) {
					mae = s[2];
			%>

			<%
				}
			%>
			<%
				if (mae.equals(s[2]) == false) { //maeはnullにつき比較できない
			%>
				<form action="./Gamen6" method="post">
				<%= User=s[4] %>
				<input type="hidden" name="TUserid" value=<%=User%>> 
				<input type="submit" value=<%=s[2]%> class="btn-square">
				<%
					mae = s[2];
				%>
				<%
					}
				%>
				<form action=# method="post">
					<div class="box30">
						<!--アレイリストの課題名を取得し表示する  -->
						<p><%=s[1]%></p> <!--repodate  -->
						<div class="box-title"><%=s[3]%></div> <!--tname(作業名)-->
						<input type="hidden" name="name" value=Name> 
						<input　type="hidden" name="Wname" value=<%=s[0]%>> 
						<input　type="hidden" name="Tname" value=<%=s[1]%>> 
						<input　type="submit" value="詳細へ" class="btn-square">
					</div>
				</form>
				<!--アレイリストの作業名を取得し表示する,報告のボタンを押下すると押下した作業の報告画面への遷移を行う  -->
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>