<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면" />
</jsp:include>
<%
request.setAttribute("test", new String[]{"1", "2", "3", "4"});
%>
<section id="content">
	<h2>Hello Spring</h2>
	<h3>ajax통신하기</h3>
	<h4>
		<button type="button" class="btn btn-outline-primary"
			onclick="basicAjax();">기본ajax처리</button>
	</h4>
	<h4>
		<button type="button" class="btn btn-outline-success"
			onclick="convertAjax();">json받기</button>
	</h4>
	<h4>
		<button class="btn btn-outline-warning" onclick="basic2();">jsp받아오기</button>
	</h4>
	<h4>
		<button class="btn btn-outline-danger" onclick="selectAll();">전체회원
			가져오기</button>
	</h4>
	<h4>
		<button class="btn btn-outline-dark" onclick="insertData();">데이터저장하기</button>
	</h4>
	<div id="ajaxContainer"></div>

	<script>
		const basicAjax=()=>{
			$.get('${pageContext.request.contextPath}/ajax/basicTest.do',(data)=>{
				console.log(data)
				$("#ajaxContainer").html("<h2>"+data+"</h2>");
			});
		}
		
		const convertAjax=()=>{
			$.get("${pageContext.request.contextPath}/ajax/converter",data=>{
				console.log(data)
			});
		}
		
		const basic2=()=>{
			$.get("${pageContext.request.contextPath}/ajax/basic2",data=>{
				$("#ajaxContainer").html(data);
			});
		}
		const selectAll=()=>{
			$.get("${pageContext.request.contextPath}/ajax/selectAll",data=>{
				console.log(data);
				const table=$("<table>");
				const header=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
				const tr=$("<tr>");
				header.forEach(e=>{
					const th=$("<th>").text(e);
					tr.append(th);
				})
				table.append(tr);
				data.forEach(e=>{
					const bodyTr=$("<tr>");
					const userId=$("<td>").text(e.userId);
					const name=$("<td>").text(e.userName);
					const age=$("<td>").text(e.age);
					const gender=$("<td>").text(e.gender);
					const email=$("<td>").text(e.email);
					const phone=$("<td>").text(e.phone);
					const address=$("<td>").text(e.address);	
					const hobby=$("<td>").html(e.hobby.toString());
					const enrollDate=$("<td>").text(new Date(e.enrollDate));
					bodyTr.append(userId).append(name).append(age)
					.append(gender).append(email).append(phone).append(address)
					.append(hobby).append(enrollDate);
					table.append(bodyTr);
				});
				$("#ajaxContainer").html(table);				
			});
		}
		const insertData=()=>{
			const data={userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
			/* $.post("${pageContext.request.contextPath}/ajax/insertData.do",
					{userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"},
					data=>{
						console.log(data);
					}); */
			/* $.ajax({
				url:"${pageContext.request.contextPath}/ajax/insertData.do",
				type:"post",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				success:data=>{
					console.log(data);
				}
			});		 */
					
					
					//fetch함수를 제공함. - > 다른라이브러리가 필요가없다.
					//fetch("URL주소",{요청에 대한 옵션})
					// .then(response=>response.json())응답내용파싱,,에러처리 
					// .then(data=>{처리로직})//success함수
					
				 /* 	fetch("${pageContext.request.contextPath}/ajax/selectAll",{
						method:"get",
						//headers:{} : 헤더에 보낼내용이있다면 이걸로 설정가능 세부적으로 옵션값을 줘서 만들어낼수가있음
						//body: JSON.stringify(객체) 
					}).then(response=>{console.log(response); if(!response.ok) throw new Error("요청실패!"); return response.json()
					}
				)
					.then(data=>{
						console.log(data)
						}
					).catch(e=>{
						alert(e);
					}); */
					
					 fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
						method:"post",
						headers:{
							"Content-type":"application/json"
						},body:JSON.stringify(data)//자바에 자바스크립트표현식을 보낼수없으니 변환해서 보내줌
					}).then(response=>{
						if(!response.ok) new Error(""); 
						return response.json()}//서버가 json으로 응답했을때
						//일반 문자로 반환했을때 response.text()
					)
					.then(data=>{
						console.log(data);
					}).catch(e=>{
						
					});
				 
								
		}
		</script>
		<h1>JPA테스트</h1>
		<h3><a href="${pageContext.request.contextPath }/jpa/basicTest.do">기본 EntityManager이용하기</a></h3>



</section>




<jsp:include page="/WEB-INF/views/common/footer.jsp" />