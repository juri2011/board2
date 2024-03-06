<%@page import="board.BoardVO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="dao" class="board.BoardDAO"></jsp:useBean>
<!-- 
자바빈 - 디자인 부분과 로직부분을 나누고, 코드의 재사용성을 증가시키기 위해 만들어진 기능

useBean 태그 : application, session, request, page 저장소에 저장된 자바 객체를 꺼낸다.
			  저장된 객체가 없다면 새로 생성하여 저장한다.
			  
			  id 속성 - 1) 꺼낸 객체의 참조변수 이름
			  		   2) getAttribute()로 값을 꺼낼 때 사용하는 이름
			  		   3) 객체를 생성 할 때 저장소에 저장하는 key 값의 이름
			  
			  기본 scope 는 page 이다.
			  
	위의 액션태그를 코드로 바꾸면 아래와 같다.(scope를 request로 설정했을 경우)
BoardDAO dao = (BoardDAO)request.getAttribute("dao");
if(dao == null){
    dao = new BoardDAO();
    request.setAttribute("dao", dao);


하지만 useBean 액션태그는 MVC 패턴에서 사용하기 적합하지 않다.(참고)
}
 -->
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardVO vo = dao.selectOne(num);
	pageContext.setAttribute("vo",vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
</head>
<body>
	<h3>글 정보</h3>
	<p>번호 : ${vo.num}</p>
	<p>제목 : ${vo.title}</p>
	<p>작성자 : ${vo.writer}</p>
	<p>내용 : ${vo.content}</p>
	<p>등록일자 : ${vo.regdate}</p>
	<p>조회수 : ${vo.cnt}</p>
	<a href="<c:url value="/board/editForm.jsp?num=${vo.num}"/>"><button>수정</button></a>
	<a href="<c:url value="/board/deleteForm.jsp?num=${vo.num}"/>"><button>삭제</button></a>
</body>
</html>