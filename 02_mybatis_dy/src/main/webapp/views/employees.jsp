<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty employees }">
		<table>
			<c:forEach var="e" items="${employees }">
				<tr>
					<td>${e.empId }</td>
					<td>${e.empName }</td>
					<td>${e.empNo }</td>
					<td>${e.email }</td>
					<td>${e.phone }</td>
					<td>
						<ul>
							<li>${e.dept.deptId }</li>
							<li>${e.dept.deptTitle }</li>
							<li>${e.dept.locationId }</li>
						</ul>
					</td>
					<td>${e.jobCode }</td>
					<td>${e.salary }</td>
					<td>${e.bonus }</td>
					<td>${e.salLevel }</td>
					<td>${e.managerId }</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>