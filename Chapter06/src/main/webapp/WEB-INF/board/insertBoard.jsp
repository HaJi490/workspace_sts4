<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 등록</title>
</head>
<body>
	<center>
		<h3>새글 등록하기</h3>
		<hr>
		<form action="insertBoard" method="post">
			<input name="seq" type="hidden" value="${ board.seq }"/>
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input name="title" type="text" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left"><input name="writer" type="text" size="10"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left"><textarea name="content" cols="40" rows="10">${ board.content }</textarea> </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="새글 등록"/>
					</td>
				</tr>
		</form>	
	</center>
</body>
</html>