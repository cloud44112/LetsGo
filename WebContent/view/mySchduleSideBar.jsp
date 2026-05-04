<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">

<aside class="sidebar">
     <ul>
       <button><a href="controller?cmd=myScheduleRouteManageUI">동선관리</a></button>
       <button><a href="controller?cmd=myScheduleBudgetDetailUI">예산관리</a></button>
       <button><a href="controller?cmd=myScheduleTodoListUI">To-Do 리스트</a></button>
    </ul>
    <div>
        <button type="button" id="dateEditBtn">날짜 수정</button>
        <div id="dateEditForm" style="display:none; margin-top:6px;">
            <form action="/LetsGo/controller" method="get">
                <input type="hidden" name="cmd" value="updateStartAt">
                <input type="date" name="startAt" id="startAtInput" value="${startAt}" required>
                <button type="submit">확인</button>
                <button type="button" id="dateCancelBtn">취소</button>
            </form>
        </div>
        <button><a href="controller?cmd=myScheduleShareUI">공유하기</a></button>
        <button type="button" id="deleteBtn">삭제하기</button>
    </div>
</aside>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const mainElement = document.querySelector('main');
        if (mainElement) {
            const deleteResult = mainElement.dataset.deleteResult;
            if (deleteResult === 'true') {
                alert('삭제되었습니다.');
                location.href = 'mylist.html';
            } else if (deleteResult === 'false') {
                alert('삭제에 실패했습니다.');
            }
        }

        const deleteBtn = document.getElementById('deleteBtn');
        if (deleteBtn) {
            deleteBtn.addEventListener('click', function () {
                if (confirm('정말 삭제하시겠습니까?')) {
                    location.href = '/LetsGo/controller?cmd=deleteSchedule';
                }
            });
        }

        const dateEditBtn = document.getElementById('dateEditBtn');
        const dateEditForm = document.getElementById('dateEditForm');
        const dateCancelBtn = document.getElementById('dateCancelBtn');

        if (dateEditBtn) {
            dateEditBtn.addEventListener('click', function () {
                dateEditForm.style.display = dateEditForm.style.display === 'none' ? 'block' : 'none';
            });
        }
        if (dateCancelBtn) {
            dateCancelBtn.addEventListener('click', function () {
                dateEditForm.style.display = 'none';
            });
        }
    });
</script>