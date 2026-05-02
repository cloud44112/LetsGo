<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/auth-common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/getid.css">
</head>

<body>

	<div class="getidbox">
		<h1>아이디 찾기</h1>
		<jsp:include page="/view/errorMessage.jsp" />
		<div id="getIdMessage"></div>
		<div id="foundIdMessage"></div>
		<form id="getIdForm" method="post"
			action="<%=request.getContextPath()%>/controller?cmd=getIdAction">
			<div class="name">
				<input id="nameInput" name="NAME" placeholder="이름">
			</div>
			<div class="emaile">
				<input type="email" id="emailInput" name="EMAIL" placeholder="이메일">

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
		<button type="button" class="back-button" onclick="history.back()">뒤로가기</button>
	</div>
	<script type="text/javascript">
		document.getElementById("getIdForm").onsubmit = function (event) {
			event.preventDefault();

			var name = document.getElementById("nameInput").value;
			var email = document.getElementById("emailInput").value;
			var message = document.getElementById("getIdMessage");
			var found = document.getElementById("foundIdMessage");

			message.style.display = "none";
			found.style.display = "none";
			message.innerText = "";
			found.innerText = "";

			if (name.trim() == "" || email.trim() == "") {
				message.style.display = "block";
				message.innerText = "이름과 이메일을 입력해주세요.";
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function () {
				if (xhr.readyState != 4) {
					return;
				}
				if (xhr.status != 200) {
					message.style.display = "block";
					message.innerText = "아이디가 없습니다.";
					return;
				}

				var data;
				try {
					data = JSON.parse(xhr.responseText);
				} catch (e) {
					message.style.display = "block";
					message.innerText = "아이디 찾기 오류.";
					return;
				}
				if (data.result == "success") {
					found.style.display = "block";
					found.innerText = "회원님의 아이디는 \"" + data.userId + "\" 입니다.";
				} else {
					message.style.display = "block";
					message.innerText = data.message;
				}
			};

			xhr.open("POST", "<%=request.getContextPath()%>/getIdAjax", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("NAME=" + encodeURIComponent(name)
					+ "&EMAIL=" + encodeURIComponent(email));
		};
	</script>
</body>
</html>
