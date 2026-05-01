<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
    
    
    
    
<aside class="sidebar">
     <ul>
       <button><a href="controller?cmd=myScheduleRouteManageUI">동선관리</a></button>
       <button><a href="controller?cmd=myScheduleBudgetDetailUI">예산관리</a></button>
       <button><a href="controller?cmd=myScheduleTodoListUI">To-Do 리스트</a></button>
    </ul>
    <div>
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
    });
</script>
</script>