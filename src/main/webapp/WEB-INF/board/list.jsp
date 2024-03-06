<%@page import="board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	BoardDAO dao = new BoardDAO();
	List<BoardVO> ls = dao.selectAll();
	pageContext.setAttribute("ls",ls);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>
	<h2>게시글 목록</h2>
	<c:forEach var="board" items="${ls}">
		<p>${board}</p>
	</c:forEach>
</body>
</html>