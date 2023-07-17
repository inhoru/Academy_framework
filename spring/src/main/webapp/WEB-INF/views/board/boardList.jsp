<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시판"/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <button  onclick="location.assign('${path}/board/boardWriteMove.do');">글쓰기</button>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="b" items="${board }">
           
           <tr>
                <th><c:out value="${b.boardNo }"/></th>
                <th><a href="${path }/board/boardInfo.do?no=${b.boardNo}"><c:out value="${b.boardTitle }"/></a></th>
                <th><c:out value="${b.boardWriter }"/></th>
                <th><c:out value="${b.boardDate }"/></th>
                <th></th>
                <th><c:out value="${b.boardReadCount }"/></th>
            </tr>
            </c:forEach>
            
        </table>
        
        <c:out value="${pageBar}" escapeXml="false" /> 
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


