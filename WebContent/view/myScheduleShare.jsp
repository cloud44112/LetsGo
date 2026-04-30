<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8"> <link rel="stylesheet" type="text/css" href="/LetsGo/view/css/myScheduleList.css">

</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />
	

<main>
    <div class="content">
        <h3>동반자 추가</h3>
        <div class="invite-row">
            <form method="post" action="controller">
                <input type="hidden" name="cmd" value="addCompanionAction">
                <input type="text" placeholder="id입력" name="sharedUserId" />
                <button type="submit">동반자 추가 하기</button>
            </form>
        </div>

        <div class="member-list">
            <c:forEach var="item" items="${colleagueList}">
                <div class="member-item">
                    <span class="member-icon">▣</span>
                    <div class="member-info">
                        <span class="member-name">${item.name}</span>
                        <span class="member-email">${item.email}</span>
                    </div>
                    <div class="member-select">
                        <select class="permission-select" data-userid="${item.userId}">
                            <option value="W" ${item.permission == 'W' ? 'selected' : ''}>편집 허용</option>
                            <option value="R" ${item.permission == 'R' ? 'selected' : ''}>읽기 허용</option>
                        </select>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 게시판 공유 폼 -->
        <form id="shareForm" action="controller">
            <input type="hidden" name="cmd" value="shareToPost">
            <input type="hidden" name="myScheduleId" value="${sessionScope.currentScheduleId}">
            
            <button class="export-btn" type="button" onclick="sendShareRequest()">게시판에 공유</button>
            <input type="checkbox" name="isAnonymous" value="1"> 익명으로 게시
        </form>
    </div>
</main>

<script type="text/javascript">

    document.querySelectorAll(".permission-select").forEach(function(target) {
        target.onchange = function() {
            const xhr = new XMLHttpRequest();
            const userId = this.getAttribute("data-userid");
            const permission = this.value;
            const url = "controller?cmd=editCompanionPermission&sharedUserId=" + userId + "&permission=" + permission;

            xhr.open("GET", url, true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    const parsed = JSON.parse(xhr.responseText);
                    if (parsed.result) {
                        alert("권한이 변경되었습니다.");
                    } else {
                        alert("권한 변경 실패!");
                    }
                }
            };
            xhr.send();
        };
    });

    function sendShareRequest() {
        const xhr = new XMLHttpRequest();
        const form = document.getElementById("shareForm");
        const formData = new FormData(form);

        xhr.open("POST", "controller?cmd=shareToPost", true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                const parsed = JSON.parse(xhr.responseText);
                if(parsed.result) {
                    alert("성공적으로 공유되었습니다!");
                } else {
                    alert("공유에 실패했습니다.");
                }
            } else {
                alert("통신 오류: " + xhr.status);
            }
        };
        xhr.send(formData);
    }
</script>
</body>
</html>