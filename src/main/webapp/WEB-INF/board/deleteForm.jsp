<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title>
</head>
<body>
<%
	int num = Integer.parseInt(request.getParameter("num"));
%>
	<form action="delete.jsp">
		<input type="hidden" value="<%=num%>" name="num" />
		<!-- history.back(); -> js에서 이전 페이지로 이동 하는 함수 -->
		삭제하시겠습니까? <input type="submit" value="예" /> <input type="button" value="아니요" onclick="history.back()"/>
	</form>
	
</body>
</html>