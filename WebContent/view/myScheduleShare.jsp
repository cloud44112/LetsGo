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
                    <option value="W" ${item.permission == 'edit' ? 'selected' : ''}>편집 허용</option>
                    <option value="R" ${item.permission == 'read' ? 'selected' : ''}>읽기 허용</option>
                </select>
            </div>
        </div>
    </c:forEach>
</div>

        <div class="export-section">
            <p>내보내기 / 공유</p>
           	<form method="post" action="controller">
           	<input type="hidden" name="cmd" value="addCompanionAction">
            <button class="export-btn" type="button">📋 게시판에 공유</button>
            <input type="checkbox" name="isAnonymous" value=1 > 익명으로 게시
            </form>
            <button class="export-btn" type="button">📄 PDF로 내보내기</button>
            <button class="export-btn" type="button">🖼️ 사진으로 내보내기</button>
        </div>
    </div>
</main>
	<script type = "text/javascript">
		let xhr = new XMLHttpRequest();
		let callbackMethod = function() {
	        if (xhr.readyState == 4 && xhr.status == 200) {
				let message = "변경되지않았습니다.";
				let parsed = JSON.parse(xhr.responseText);
				if (parsed.result){
					alert("권한이 변경되었습니다.");
				}
				else
					{alert("권한이 변경되지 않았습니다.");
					}
				
					
	        }
	    }
		xhr.onreadystatechange = callbackMethod;
		let inputs = document.querySelectorAll(".permission-select");
		inputs.forEach(function(target) {
	        target.onchange = function() {
	            let userId = this.getAttribute("data-userid");
	            let permission = this.value;
	            
	            let url = "controller?cmd=editCompanionPermission&sharedUserId=" + userId + "&permission=" + permission;
	            xhr.open("get", url, true);
	            xhr.send(null);
	        };
	    });
		
		
	</script>
</body>
</html>