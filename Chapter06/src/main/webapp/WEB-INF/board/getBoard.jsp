<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
								"http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세</title>
</head>
<body>
	<center>
	<h1>게시글 상세</h1>
	<hr>
	<form action="updateBoard" method="post">
		<input name="seq" type="hidden" value="${ board.seq }"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input name="title" type="text" value="${ board.title }"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left">${ board.writer }</td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left"><textarea name="content" cols="40" rows="10">${ board.content }</textarea> </td>
			</tr>
			<tr>
				<td bgcolor="orange">등록일</td>
				<td align="left"><fmt:formatDate value="${ board.createDate }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			</tr>
			<tr>
				<td bgcolor="orange">조회수</td>
				<td align="left">${ board.cnt }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 수정"/>
				</td>
			</tr>
		</table>
	</form>
	<hr>
		<a href="insertBoard">글 등록</a>&nbsp;&nbsp;&nbsp; <!-- a태그는 get메서드 -->
		<a href="deleteBoard?seq=${ board.seq }">글 삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getBoardList">글 목록</a>&nbsp;&nbsp;&nbsp;
	</center>
</body>
</html>