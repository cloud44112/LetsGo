<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	margin: 0;
}

.signupbox {
	background-color: white;
	width: 400px;
	border: 1px solid;
	padding: 30px;
	box-sizing: border-box;
	text-align: left;
}

.name, .id, .email, .pw {
	margin-bottom: 15px;
	display: flex;
	align-items: center;
}

.signupbox>div {
	margin-bottom: 10px;
	padding: 5px 0;
}

input {
	flex-grow: 1;
	padding: 8px;
	margin-left: 10px;
	border: 1px solid;
	border-radius: 4px;
}

button {
	width: 100%;
	padding: 10px;
	border: 1px solid;
	margin-bottom: 15px;
}

a {
	color: #007bff;
	text-decoration: none;
	margin: 0 5px;
	display: block;
	text-align: center;
}

a:hover {
	text-decoration: underline;
}
</style>
<meta charset="UTF-8">
</head>
<body>

	<div class="signupbox">
	<h1>회원가입</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<form method="post" action="<%=request.getContextPath()%>/controller?cmd=signupAction">
			<div class="name">
				<label for="nameInput">이름</label><input id="nameInput" name="NAME" placeholder="이름">
			</div>
			<div class="id">
				<label for="idInput">아이디</label><input id="idInput" name="USER_ID" placeholder="아이디">
			</div>
			<div class="email">
				<label for="emailInput">이메일</label><input id="emailInput" name="EMAIL" placeholder="이메일">
			</div>

			<div class="pw">
				<label for="pwInput">비밀번호</label><input id="pwInput" name="PASSWORD" type="password" placeholder="비밀번호">
			</div>
			<div class="pw">
				<label for="pwConfirmInput">비밀번호 확인</label><input id="pwConfirmInput" name="PASSWORD_CONFIRM" type="password" placeholder="비밀번호 확인">
			</div>

			<div>
				<button type="submit">회원가입</button>
			</div>
		</form>
		<div>
			<a href="<%=request.getContextPath()%>/controller?cmd=loginUI">로그인</a>
		</div>
	</div>
</body>
</html>
