<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<link rel="stylesheet" type="text/css" href="css/mySchedule.css">
</head>
<body>

<aside class="sidebar">
     <ul>
       <button><a href="mylist.html">동선관리</a></button>
       <button><a href="priceManage.html">예산관리</a></button>
       <button><a href="todoList.jsp">To-Do 리스트</a></button>
    </ul>
    <div>
        <button type="button">공유하기</button>
        <button type="button">저장하기</button>
        <button type="button" id="deleteBtn">삭제하기</button>
    </div>
</aside>

<main data-delete-result="${requestScope.deleteResult}">
    <header>
        <div class="logo">레스고!</div>
        <ul class="menu" style="list-style:none; display:flex; padding:0; margin:0;">
            <li style="margin-right: 20px;"><a href="index.html">홈</a></li>
            <li style="margin-right: 20px;"><a href="leisere.html">플레이스 조회</a></li>
            <li style="margin-right: 20px;"><a href="schedulepostAll.html">일정게시판</a></li>
            <li style="margin-right: 20px;"><a href="#">내일정</a></li>
            <li><a href ="login.html">로그인</a></li>
        </ul>
    </header>

    <div class="content-container">
        <div class="content-left">
            <div class="search-area">
                <input type="text" placeholder="내일정1">
                <button type="button">수정하기</button>
                <button type="button">+</button>
            </div>
            <ul id="sortableList">
                <li class="sortable-item">1번 요소</li>
                <li class="sortable-item">2번 요소</li>
                <li class="sortable-item">3번 요소</li>
                <li class="sortable-item">4번 요소</li>
            </ul>
        </div>
        <div class="divider"></div>
        <div class="content-right">
            <div class="memo">
                <div>
                    <h3>To-Do</h3>
                </div>
                <textarea
                    rows="40"
                    cols="20"
                    placeholder="1. 금원 수원 수영장 - 수영복 챙기기"></textarea>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    const deleteResult = document.querySelector('main').dataset.deleteResult;

    if (deleteResult === 'true') {
        alert('삭제되었습니다.');
        location.href = 'mylist.html';
    } else if (deleteResult === 'false') {
        alert('삭제에 실패했습니다.');
    }

    document.getElementById('deleteBtn').addEventListener('click', function () {
        if (confirm('정말 삭제하시겠습니까?')) {
            location.href = '/LetsGo/controller?cmd=deleteSchedule';
            console.log(location.getParmeter);
        }
        
    });

    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>

</body>
</html>
