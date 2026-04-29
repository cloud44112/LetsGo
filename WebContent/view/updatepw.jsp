<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.updatepwdbox {
	width: 350px;
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

.updatepwdbox>div,
.updatepwdbox>form>div {
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
<meta charset="UTF-8">
</head>

<body>
	<div class="updatepwdbox">
	<h1>비밀번호 찾기</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<form method="post" action="<%=request.getContextPath()%>/controller?cmd=updatepwAction">
		<div class="id">
			<input name="USER_ID" placeholder="아이디">
		</div>
		<div class="emaile">
			<input type="email" name="EMAIL" placeholder="이메일">
		</div>	
		<div>
			<input type="password" name="NEW_PASSWORD" placeholder="새비밀번호">
		</div>
		<div>
			<input type="password" name="NEW_PASSWORD_CONFIRM" placeholder="새비밀번호 확인">
		</div>

		<div>
			<button type="submit">비밀번호 변경</button>
			<a href="<%=request.getContextPath()%>/controller?cmd=loginUI">로그인</a>
		</div>
		</form>
		<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	</div>
</body>
</html>
