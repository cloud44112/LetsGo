<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.getidbox {
	width: 400px;
	border: 1px solid black;
	padding: 10px;
	box-sizing: border-box;
}

.id {
	width: 100%;
	height: 50px;
	margin-bottom: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.pw {
	width: 100%;
	height: 50px;
	margin-bottom: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.getidbox>div, .getidbox>form>div {
	border: 1px solid;
	margin-bottom: 10px;
	text-align: center;
	padding: 5px 0;
}

input {
	width: 80%;
	padding: 8px;
	margin: auto;
}
</style>
<meta charset="UTF-8">
</head>

<body>

	<div class="getidbox">
		<jsp:include page="/view/errorMessage.jsp" />
		<form method="post"
			action="<%=request.getContextPath()%>/controller?cmd=getIdAction">
			<div class="name">
				<input name="NAME" placeholder="이름">
			</div>
			<div class="emaile">
				<input type="email" name="EMAIL" placeholder="이메일">

			</div>


			<div>
				<button type="submit">아이디 찾기</button>
			</div>
		</form>
		<c:if test="${not empty requestScope.foundUserId}">
			<div>
				회원님의 아이디는 "
				<c:out value="${requestScope.foundUserId}" />
				" 입니다.
			</div>
		</c:if>
		<div>
			<a href="<%=request.getContextPath()%>/controller?cmd=loginUI">로그인</a>
		</div>
	</div>
</body>
</html>
