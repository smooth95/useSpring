<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<%
    // 현재 시간 가져오기
    LocalDateTime now = LocalDateTime.now();
    // 타임스탬프 포맷 (년월일시분초)
    String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
%>
<script src="resources/static/js/excel.js?ver=<%= timestamp %>"></script>
<body>
	Excel 작업 페이지
	
	<!--  home 으로 이동  -->
	<input type="button" value="Home" onClick="location.href='/test'">
	<input type="button" value="ExcelDownload" onClick="location.href='/test/excelDownload'">
	
	<table border="1">
		<thead>
			<tr>
				<td>id</td>
				<td>pw</td>
				<td>name</td>
				<td>age</td>
				<td>address</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>aid</td>
				<td>apw</td>
				<td>aname</td>
				<td>aage</td>
				<td>aaddress</td>
			</tr>
		</tbody>
	</table>
</body>
</html>