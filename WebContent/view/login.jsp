<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
body {
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.loginbox {
	width: 300px;
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

.loginbox>div, .loginbox>form>div {
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

.back-button {
	position: fixed;
	top: 16px;
	left: 16px;
	z-index: 1000;
	padding: 8px 12px;
}
</style>
</head>

<body>
	<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	<div class="loginbox">
		<h1>로그인</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<form method="post"
			action="<%=request.getContextPath()%>/controller?cmd=loginAction">
			<div class="id">
				<input name="USER_ID" placeholder="아이디">
			</div>
			<div class="pw">
				<input type="password" name="PASSWORD" placeholder="비밀번호">
			</div>
			<div>
				<button type="submit">로그인</button>
			</div>
		</form>
		<div>
			<a href="<%=request.getContextPath()%>/controller?cmd=getIdUI">아이디
				찾기</a> <a href="<%=request.getContextPath()%>/controller?cmd=updatepwUI">비밀번호
				찾기</a> <a href="<%=request.getContextPath()%>/controller?cmd=signupUI">회원가입</a>
		</div>
		
	</div>
</body>
</html>
