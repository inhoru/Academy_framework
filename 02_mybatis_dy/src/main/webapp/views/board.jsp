<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${board!=null }">
		<ul>
			<li>${board.boardNo }</li>
			<li>${board.boardTitle }</li>
			<li>${board.boardWriter.userId }</li>
			<li>${board.boardContent }</li>
			<li>${board.boardDate }</li>
			<li>${board.boardReadcount }</li>
			<li><c:if test="${not empty board.boardComment }">
					<c:forEach var="c" items="${board.boardComment}">
						<table>
							<tr>
								<td>${c.boardCommentWriter.userId }</td>
		
							</tr>
							<tr>
							<td>${c.boardCommentContent }</td>
							</tr>
						</table>
					</c:forEach>
				</c:if></li>

		</ul>
	</c:if>

</body>
</html>