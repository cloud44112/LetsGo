<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
    
<aside class="sidebar">
     <ul>
       <button><a href="controller?cmd=postScheduleRouteManageUI">동선관리</a></button>
       <button><a href="controller?cmd=postScheduleBudgetDetailUI">예산관리</a></button>
       <button><a href="controller?cmd=postScheduleTodoListUI">To-Do 리스트</a></button>
    </ul>
    <div>
    	<c:if test="${match}">
       		<button type="button" id="deleteBtn">삭제하기</button>
       </c:if>
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
                    location.href = '/LetsGo/controller?cmd=deletePostSchedule&postId=${postId}';
                }
            });
        }
    });
</script>