<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전체사원조회</h2>
	<a href="${path }/employee/employeeAll.do">전체사원 조회하기</a>
	
	<h2>Data 연관관계설정하기</h2>
	
	<h3>
		<a href="${path }/emp/assciation.do">join인으로 객체 가져오기</a>
	</h3>
	
	<h2>부서 조회하기</h2>
	
	<h3>
		<a href="${path }/selectDeptAll.do">부서 조회하기</a>
	</h3>
	
	<h2>다른환경 접속하기</h2>
	
	<h3>
		<a href="${path }/member.do">회원가져오기</a>
	</h3>
	
		
	<h2>게시글 가져오기</h2>
	
	<h3>
		<a href="${path }/board.do?no=109">게시글&댓글, 작성자 이름, 이메일 출력</a>
	</h3>
	
	
	
</body>
</html>