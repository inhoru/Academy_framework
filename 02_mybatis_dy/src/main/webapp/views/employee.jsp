<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>
</head>
<body>
<h2>사원조회결과</h2>
	<form action="${pageContext.request.contextPath }/searchEmp.do" method="post">
		<table class="table">
			<tr>
				<td>
					<select name="type">
						<option value="emp_id">사원번호</option>
						<option value="emp_name">사원이름</option>
						<option value="email">사원이메일</option>
						<option value="phone">전화번호</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword"/>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
				<label><input type="radio" name="gender" value="M">남</label>
				</label><input type="radio" name="gender" value="F">여</label>
				</td>
			</tr>
			<tr>
				<td>급여</td>
				<td>
				<input type="number" name="salary" step="500000" min="1200000">
				<label><input type="radio" name="salFlag" value="ge">이상</label>
					<label><input type="radio" name="salFlag" value="le">이하</label>
				</td>
			</tr>
			<tr>
				<td>
					부서조회
				</td>
				<td>
					<label><input type="checkbox" name="deptCode" value="D9">총무부</label>
					<label><input type="checkbox" name="deptCode" value="D1">인사관리부</label>
					<label><input type="checkbox" name="deptCode" value="D2">회계관리부</label>
					<label><input type="checkbox" name="deptCode" value="D3">마케팅부</label>
					<label><input type="checkbox" name="deptCode" value="D4">국내영업부</label>
					<label><input type="checkbox" name="deptCode" value="D5">해외영업1부</label>
					<label><input type="checkbox" name="deptCode" value="D6">해외영업2부</label>
					<label><input type="checkbox" name="deptCode" value="D7">해외영업3부</label>
					<label><input type="checkbox" name="deptCode" value="D8">기술지원부</label>
					
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn btn-primary">검색</button>
				</td>
		</table>
	</form>
	<table class="table text-center">
		<tr>
				<th>사원번호</th>
				<th>사원이름</th>
				<th>주민번호</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>부서코드</th>
				<th>직급코드</th>
				<th>월급등급</th>
				<th>월급</th>
				<th>보너스</th>
				<th>매니저아이디</th>
				<th>입사일</th>
				<th>퇴사일</th>
				<th>퇴사여부</th>
				<th>성별</th>
			</tr>
		
		<c:forEach var="e" items="${employee }">
			<tr>
				<td><c:out value="${e.empId }" /></td>
				<td><c:out value="${e.empName }" /></td>
				<td><c:out value="${e.empNo }" /></td>
				<td><c:out value="${e.email }" /></td>
				<td><c:out value="${e.phone }" /></td>
				<td><c:out value="${e.deptCode }" /></td>
				<td><c:out value="${e.jobCode }" /></td>
				<td><c:out value="${e.salLevel }" /></td>
				<td><fmt:formatNumber value="${e.salary }" type="currency"/>원</td>
				<td><fmt:formatNumber value="${e.bonus }" type="percent"/></td>
				<td><c:out value="${e.managerId }" /></td>
				<td><fmt:formatDate value="${e.hireDate }"
						pattern="yyyy-MM-dd (E) hh:mm:ss" /></td>
				<td><c:out value="${e.entDate }" /></td>
				<td><c:out value="${e.entYn }" /></td>
				<td><c:out value="${e.gender=='F'?'여':'남' }" /></td>

			</tr>
		</c:forEach>
	</table>
	<c:out value="${pageBar }" escapeXml="false" />
</body>
</html>