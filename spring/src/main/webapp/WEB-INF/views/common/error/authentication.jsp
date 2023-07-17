<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 style="color:red"><%=exception.getMessage() %></h3>
	<h4>접글불가능이다람쥐</h4>
	<script>
		setTimeout(()=>{
			location.replace("${pageContext.request.contextPath}");
		},3000)
	</script>
</body>
</html>