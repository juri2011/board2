<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<h3>수정하기</h3>
	<form action="edit.jsp" method="post">
		<input type="hidden" name="num" value="${vo.num}" /><br />
		<input type="text" name="title" value="${vo.title}" required /><br />
		<input type="text" name="writer" value="${vo.writer}" required /><br />
		<textarea name="content" cols="20" rows="4">${vo.content}</textarea><br />
		<input type="submit" value="수정" />
	</form>
</body>
</html>