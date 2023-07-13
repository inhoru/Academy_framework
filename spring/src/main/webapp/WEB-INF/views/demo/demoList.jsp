<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정하기</th>
		</tr>
		<c:if test="${not empty demo }">
			<c:forEach var="demo" items="${demo }">
			<tr>
				<td>${demo.devNo }</td>
				<td>${demo.devName }</td>
				<td>${demo.devAge }</td>
				<td>${demo.devEmail }</td>
				<td>${demo.devGender }</td>
				<td>
					<!-- ${Arrays.toString(demo.devLang)} -> forEach 대신 가능, import 따로 해줘야 함 -->
					<c:forEach var="d" items="${demo.devLang }">
						${d }
					</c:forEach>
				</td>
				<td scope="col"><button class="btn btn-primary" onclick="updateDemo(${demo.devNo});">수정</button></td>
			</tr>
			</c:forEach>
		</c:if>
	</table>	
</section>
<script>
	const updateDemo=(no)=>{
		
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>