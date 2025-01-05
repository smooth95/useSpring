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
    // ���� �ð� ��������
    LocalDateTime now = LocalDateTime.now();
    // Ÿ�ӽ����� ���� (����Ͻú���)
    String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
%>
<body>
	<input type="button" value="Home" onClick="location.href='/test'">
	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bab934d8403bfc5089f416f519c865c"></script>
	<script src="resources/static/js/map.js?ver=<%= timestamp %>"></script>
</body>
</html>