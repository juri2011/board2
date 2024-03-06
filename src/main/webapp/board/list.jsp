<%@page import="board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

	/*
		문제점!!! 게시글을 수정하고 뒤로가기 버튼으로 목록 페이지에 왔을 때,
		새로고침 하기 전까지는 DB가 반영되어 있지 않는 현상을 볼 수 있다.
		흔히 사이트에서 뒤로가기를 눌렀을 때 가끔 '만료된 페이지입니다' 하고 경고 메시지가 뜨는데
		바로 이런 현상을 말하는 것이다.
		
		캐시?
			클라이언트가 html, image, js, css 등을 처음 요청했을 때
			파일의 복사본을 특정 위치에 저장하고
			이후에 같은 리소스 요청을 받으면 저장된 복사본을 사용하여
			응답시간과 네트워크 트래픽을 줄이는 방식이다.
		
		브라우저가 캐시를 사용하기 때문에 뒤로가기를 눌러도 최신 데이터가 반영되지 않는 것이다.
		이를 방지하기 위해서 브라우저가 현재 페이지 내용을 캐시에 저장하지 않도록 설정하는 방법이 있다.
		response의 헤더에 캐시 지시자를 사용하는 것이다.
	*/
	/*
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-Store");
		이 코드는 아래와 같이 쓸 수 있다.
	*/
	
	/*
		no-cache: 이름 때문에 헷갈릴 수 있지만 캐시를 생성하지 않는 것이 아니다!
				  캐시를 생성하나, 리소스를 요청할 때 서버가 항상 캐시의 유효성을 검증하도록 한다.
		no-store: 캐싱하지 않는다.
		must-revalidate: 캐시가 만료된 후 첫 조회 시 서버에서 유효성 검증하도록 한다.
	*/
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //Http1.1
	response.setHeader("Pragma", "no-cache");//http1.0(브라우저가 http1.1을 사용하지 않을 때)
	/*
		Expires를 1L로 설정하는 경우가 있는데, 제일 처음 날짜인 1970-01-01 00:00:00 의 시간으로 설정하는것이다.
		과거의 시간을 사용함으로써 캐시가 무조건 만료될수 있도록 한다. -> 캐시를 사용하지 않기 위해 지정한다.
	*/
	response.setDateHeader("Expires", 0);//http 1.0 proxy
	
	
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
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${ls}">
		<!-- setAttribute로 저장된 객체변수는 .(점)으로 가져올 수 있다 -->
			<tr>
				<td>${board.num}</td>
				<!-- request를 pageConetext에서 받아오고, contextPath는 프로젝트명을 가져옴 + get방식 -->
				<td><a href="${pageContext.request.contextPath}/board/boardDetail.jsp?num=${board.num}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>
	</table>
	<a href='${pageContext.request.contextPath}/board/registForm.jsp'><button>글등록</button></a>
</body>
</html>