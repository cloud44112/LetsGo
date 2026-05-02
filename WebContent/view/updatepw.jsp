<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/auth-common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/updatepw.css">
</head>

<body>
	<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	<div class="updatepwdbox">
	<h1>비밀번호 찾기</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<div id="updatePwMessage"></div>
		<form id="updatePwForm" method="post" action="<%=request.getContextPath()%>/controller?cmd=updatepwAction">
		<div class="id">
			<input id="userIdInput" name="USER_ID" placeholder="아이디">
		</div>
		<div class="emaile">
			<input type="email" id="emailInput" name="EMAIL" placeholder="이메일">
		</div>	
		<div class="pw">
			<input type="password" id="newPasswordInput" name="NEW_PASSWORD" placeholder="새비밀번호">
		</div>
		<div class="pw">
			<input type="password" id="newPasswordConfirmInput" name="NEW_PASSWORD_CONFIRM" placeholder="새비밀번호 확인">
		</div>

		<div>
			<button type="submit">비밀번호 변경</button>
			<a href="<%=request.getContextPath()%>/controller?cmd=loginUI">로그인</a>
		</div>
		</form>
	</div>
	<script type="text/javascript">
		document.getElementById("updatePwForm").onsubmit = function (event) {
			event.preventDefault();

			var userId = document.getElementById("userIdInput").value;
			var email = document.getElementById("emailInput").value;
			var newPassword = document.getElementById("newPasswordInput").value;
			var newPasswordConfirm = document.getElementById("newPasswordConfirmInput").value;
			var message = document.getElementById("updatePwMessage");

			message.style.display = "none";
			message.innerText = "";
			message.style.color = "red";

			if (userId.trim() == "" || email.trim() == "" || newPassword.trim() == "" || newPasswordConfirm.trim() == "") {
				message.style.display = "block";
				message.innerText = "필수 항목을 모두 입력해주세요.";
				return;
			}

			if (newPassword != newPasswordConfirm) {
				message.style.display = "block";
				message.innerText = "새 비밀번호 확인이 일치하지 않습니다.";
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function () {
				if (xhr.readyState != 4) {
					return;
				}
				if (xhr.status != 200) {
					message.style.display = "block";
					message.innerText = "비밀번호 변경을 다시해주세요.";
					return;
				}

				var data;
				try {
					data = JSON.parse(xhr.responseText);
				} catch (e) {
					message.style.display = "block";
					message.innerText = "비밀번호 변경 오류.";
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

			xhr.open("POST", "<%=request.getContextPath()%>/updatePwAjax", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("USER_ID=" + encodeURIComponent(userId)
					+ "&EMAIL=" + encodeURIComponent(email)
					+ "&NEW_PASSWORD=" + encodeURIComponent(newPassword)
					+ "&NEW_PASSWORD_CONFIRM=" + encodeURIComponent(newPasswordConfirm));
		};
	</script>
</body>
</html>
