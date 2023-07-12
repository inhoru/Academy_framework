<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="출력테스트" />
</jsp:include>
<section id="content">
	<form id="devFrm" method="post">
		<table class="table">
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">나이</th>
				<th scope="col">이메일</th>
				<th scope="col">성별</th>
				<th scope="col">개발가능언어</th>
				<th scope="col">수정</th>
			</tr>
			<c:forEach var="d" items="${demo }">
				<tr>
					<td scope="col">${d.devNo }</td>
					<td scope="col">${d.devName }</td>
					<td scope="col">${d.devAge }</td>
					<td scope="col">${d.devEmail }</td>
					<td scope="col">${d.devGender }</td>
					<td scope="col"><c:forEach var="m" items="${d.devLang }">
					${m}
				</c:forEach></td>
					<td scope="col"><button class="btn btn-outline-primary"
							onclick="updateDemo('${d.devNo}')">수정</td>
				</tr>
			</c:forEach>
			데이터출력하기
		</table>
	</form>
</section>
<script>
const updateDemo = (e) => {
	$("#devFrm").attr("action", "${path}/demo/updateDemoMove.do?no="+e);
	$("#devFrm").submit();
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />