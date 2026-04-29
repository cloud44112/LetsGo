<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-left">
    <div class="schedule-header">
        <input type="text" name="title" id="scheduleTitleInput" value="${scheduleTitle}" placeholder="일정 제목을 입력하세요" class="title-input">
        <button type="button" id="saveTitleBtn" class="save-title-btn">수정</button>
    </div>
    <ul id="sortableList">
        <c:forEach var="route" items="${ScheduleRoute}">
            <li class="sortable-item" data-visit-id="${route.visitId}">
                ${route.visitOrder}. ${route.title}
            </li>
        </c:forEach>
    </ul>
    <div class="content-left-bottom">
        <button type="button">저장하기</button>
    </div>
</div>

<script>
    if ("${updateTitleResult}" === "true") {
        alert("일정 제목이 수정되었습니다.");
    }

    document.getElementById('saveTitleBtn').addEventListener('click', function() {
        const title = document.getElementById('scheduleTitleInput').value;
        if (!title) {
            alert('제목을 입력해주세요.');
            return;
        }
        
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/LetsGo/controller?cmd=updateScheduleTitle';
        
        const titleInput = document.createElement('input');
        titleInput.type = 'hidden';
        titleInput.name = 'title';
        titleInput.value = title;
        form.appendChild(titleInput);
        
        document.body.appendChild(form);
        form.submit();
    });
</script>
