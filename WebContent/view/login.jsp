<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/auth-common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/login.css">
</head>

<body>
	<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	<div class="loginbox">
		<h1>로그인</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<div id="loginMessage"></div>
		<form id="loginForm" method="post"
			action="<%=request.getContextPath()%>/controller?cmd=loginAction">
			<div class="id">
				<input id="userId" name="USER_ID" placeholder="아이디">
			</div>
			<div class="pw">
				<input type="password" id="password" name="PASSWORD" placeholder="비밀번호">
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
	<script type="text/javascript">
		document.getElementById("loginForm").onsubmit = function (event) {
			event.preventDefault();

			var userId = document.getElementById("userId").value;
			var password = document.getElementById("password").value;
			var message = document.getElementById("loginMessage");
			message.style.display = "none";
			message.innerText = "";

			if (userId.trim() == "" || password.trim() == "") {
				message.style.display = "block";
				message.innerText = "아이디/비밀번호를 입력해주세요.";
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function () {
				if (xhr.readyState != 4) {
					return;
				}

				if (xhr.status != 200) {
					message.style.display = "block";
					message.innerText = "🚨아이디 또는 비밀번호를 확인🚨.";
					return;
				}

				var data;
				try {
					data = JSON.parse(xhr.responseText);
				} catch (e) {
					message.style.display = "block";
					message.innerText = "로그인 오류입니다.";
					return;
				}
				if (data.result == "success") {
					location.href = data.url;
				} else {
					message.style.display = "block";
					message.innerText = data.message;
				}
			};

			xhr.open("POST", "<%=request.getContextPath()%>/loginAjax", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("USER_ID=" + encodeURIComponent(userId)
					+ "&PASSWORD=" + encodeURIComponent(password));
		};
	</script>
</body>
</html>
