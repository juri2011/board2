<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="board.BoardVO"/>
<jsp:useBean id="dao" class="board.BoardDAO"/>
<!--
	setProperty 액션태그는 자바빈 파일의 setter 메소드를 이용하기 위해 사용된다.
	useBean 액션태그로 생성한 자바빈 객체에 대해서 프로퍼티(필드) 에 값을 설정한다.
	property 속성에 *을 사용하면 setter 메소드가 있는 모든 필드에 값을 설정한다.
-->
<jsp:setProperty name="vo" property="*"/>

<%
	dao.insert(vo);
	//response.sendRedirect(request.getContextPath() + "list.jsp");
	
%>
<c:redirect url="${pageContext.request.contextPath}/list.jsp"/>