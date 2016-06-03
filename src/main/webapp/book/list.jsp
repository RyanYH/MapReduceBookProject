<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sist Book Store</title>
<link rel="stylesheet" type="text/css" href="book/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		var i = 0;
		$('.aImg').hover(function(){
				i=$(this).attr("value");
				$('#m'+i).css({opacity:0.2});
		},function(){
				$('#m'+i).css({opacity:1});
		});
	});
</script>
</head>
<body>
	<center>
		<h3>베스트 셀러 목록</h3>
		<table id="table_content">
			<tr>
				<td align="right">
					<a href="total.do">베스트셀러러 전체통계</a>
				</td>
			</tr>
		</table>
	
		<table id="table_content">
			<tr>
				<c:forEach var="vo" items="${list }" begin="0" end="9">
					<td>
						<a href="detail.do?no=${vo.no }" class="aImg" value="${vo.no }">
						<img src="${vo.poster }" width="120" height="150" border=0 id="m${vo.no }">
						</a>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="vo" items="${list }" begin="0" end="9">
					<td>
						${vo.title }
					</td>
				</c:forEach>
			</tr>
		    <tr>
				<c:forEach var="vo" items="${list }" begin="0" end="9">
					<td>
						<font color="red">예매율&nbsp;&nbsp;</font>${vo.author }%
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="vo" items="${list }" begin="0" end="9">
					<td>
						♡${vo.publisher }
					</td>
				</c:forEach>
			</tr>
		</table>


		<table border=0 width=900>
		    <tr>
				<td>
					<table id="table_content" style="width:300px">
						<tr>
							<th>영화 Ranking</th>
						</tr>
<%-- 						<c:forEach var="str" items="${raList }" varStatus="status">
					    <tr>
							<td>${status.index+1}위.&nbsp;${str }</td>
						</tr>
						</c:forEach> --%>
					</table>
				</td>
				<td>
<%-- 					<table id="table_content" style="width:300px">
						<tr>
							<th>영화 예매순</th>
						</tr>
						<c:forEach var="str" items="${reList }" varStatus="status">
					    <tr>
							<td>${status.index+1}위.&nbsp;${str }</td>
						</tr>
						</c:forEach>
					</table> --%>
				</td>
				<td>
					<table id="table_content" style="width:300px">
						<tr>
							<th>영화 박스오피스</th>
						</tr>
						<%-- <c:forEach var="str" items="${bList }" varStatus="status">
					    <tr>
							<td>${status.index+1}위.&nbsp;${str }</td>
						</tr>
						</c:forEach> --%>
					</table>
				</td>
			</tr>
		</table>	
	</center>
</body>
</html>