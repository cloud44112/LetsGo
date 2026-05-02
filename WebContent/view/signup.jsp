<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/auth-common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/signup.css">
</head>
<body>

	<div class="signupbox">
	<h1>회원가입</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<div id="signupMessage"></div>
		<form id="signupForm" method="post" action="<%=request.getContextPath()%>/controller?cmd=signupAction">
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
		<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	</div>
	<script type="text/javascript">
		document.getElementById("signupForm").onsubmit = function (event) {
			event.preventDefault();

			var name = document.getElementById("nameInput").value;
			var userId = document.getElementById("idInput").value;
			var email = document.getElementById("emailInput").value;
			var password = document.getElementById("pwInput").value;
			var passwordConfirm = document.getElementById("pwConfirmInput").value;
			var message = document.getElementById("signupMessage");

			message.style.display = "none";
			message.innerText = "";
			message.style.color = "red";

			if (name.trim() == "" || userId.trim() == "" || email.trim() == "" || password.trim() == "" || passwordConfirm.trim() == "") {
				message.style.display = "block";
				message.innerText = "필수 항목을 모두 입력해주세요.";
				return;
			}

			if (password != passwordConfirm) {
				message.style.display = "block";
				message.innerText = "비밀번호 확인이 일치하지 않습니다.";
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function () {
				if (xhr.readyState != 4) {
					return;
				}
				if (xhr.status != 200) {
					message.style.display = "block";
					message.innerText = "회원가입을 다시해주세요.";
					return;
				}

				var data;
				try {
					data = JSON.parse(xhr.responseText);
				} catch (e) {
					message.style.display = "block";
					message.innerText = "회원가입 오류.";
					return;
				}
				message.style.display = "block";
				message.innerText = data.message;

				if (data.result == "success") {
					message.style.color = "black";
					setTimeout(function () {
						location.href = data.url;
					}, 700);
				} else {
					message.style.color = "red";
				}
			};

			xhr.open("POST", "<%=request.getContextPath()%>/signupAjax", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("NAME=" + encodeURIComponent(name)
					+ "&USER_ID=" + encodeURIComponent(userId)
					+ "&EMAIL=" + encodeURIComponent(email)
					+ "&PASSWORD=" + encodeURIComponent(password)
					+ "&PASSWORD_CONFIRM=" + encodeURIComponent(passwordConfirm));
		};
	</script>
</body>
</html>
