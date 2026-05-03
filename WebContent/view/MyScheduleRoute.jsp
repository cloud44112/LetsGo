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
                <button type="button" class="delete-visit-btn" onclick="deleteVisit('${route.visitId}')">×</button>
            </li>
        </c:forEach>
    </ul>
    <div class="content-left-bottom">
        <button type="button" id="updateRouteBtn">수정하기</button>
        <button type="button" id="addVisitBtn">추가하기</button>
    </div>
</div>

<script>
    if ("${updateTitleResult}" === "true") {
        alert("일정 제목이 수정되었습니다.");
    }
    
    if ("${deleteVisitItemResult}" === "true") {
        alert("항목이 삭제되었습니다.");
    }
    
    if ("${updateRouteResult}" === "true") {
        alert("동선이 수정되었습니다.");
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

    function deleteVisit(visitId) {
        if (confirm('이 항목을 삭제하시겠습니까?')) {
            location.href = '/LetsGo/controller?cmd=deleteVisitItem&visitItemId=' + visitId;
        }
    }

    document.getElementById('updateRouteBtn').addEventListener('click', function() {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/LetsGo/controller?cmd=updateRouteOrder';

        const items = document.querySelectorAll('#sortableList li');
        items.forEach((item, index) => {
            const idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'visitItemId';
            idInput.value = item.dataset.visitId;
            form.appendChild(idInput);

            const orderInput = document.createElement('input');
            orderInput.type = 'hidden';
            orderInput.name = 'visitOrder';
            orderInput.value = index + 1;
            form.appendChild(orderInput);
        });

        document.body.appendChild(form);
        form.submit();
    });

    document.getElementById('addVisitBtn').addEventListener('click', function() {
        location.href = '/LetsGo/controller?cmd=indexUI';
    });
</script>
