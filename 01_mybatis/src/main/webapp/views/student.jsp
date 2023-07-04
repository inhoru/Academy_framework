<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>학생조회결과</title>
</head>
<body>
 <h2>학생정보</h2>

 <h3>전체 학생</h3>
 
 <c:if test="${not empty students }">
  <h3>전체 학생 수 : <c:out value="${count}" /></h3>
 <c:forEach var="students" items="${students }">
 <ul>
 	<li>학생이름 : <c:out value="${students.studentName }"/></li>
 	<li>학생전화번호 : <c:out value="${students.studentTel }"/></li>
 	<li>학생이메일 : <c:out value="${students.studentEmail }"/></li>
 	<li>학생주소 : <c:out value="${students.studentAddress }"/></li>
 	<li>등록일: <c:out value="${students.reg_date }"/></li>
 </ul>
 </c:forEach>
 </c:if>
 
 

 

</body>
</html>