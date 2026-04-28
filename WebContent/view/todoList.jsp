<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
  <style>
        body {
            margin: 0;
            display: flex;
            height: 100vh;
            font-family: system-ui, sans-serif;
        }

        aside.sidebar {
            width: 220px;
            border-right: 1px solid #000;
            padding: 10px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 100vh;
        }

        aside.sidebar ul {
            list-style: none;
            padding: 0;
            margin: 0 0 20px 0;
        }

        aside.sidebar li {
            margin-bottom: 10px;
        }

        aside.sidebar button {
            width: 100%;
            padding: 6px 0;
            margin-bottom: 10px;
            border: 1px solid #000;
            background: transparent;
            cursor: pointer;
            font-size: 1rem;
        }

        main {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }

        header {
            height: 50px;
            border-bottom: 1px solid #000;
            padding: 10px 20px;
            box-sizing: border-box;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-weight: 600;
        }

        .content-container {
            flex-grow: 1;
            display: flex;
        }

        .content-left, .content-right {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
        }

        .divider {
            width: 1px;
            background-color: #000;
        }

        .search-area {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 20px;
        }

        .search-area input[type="text"] {
            flex-grow: 1;
            padding: 6px 8px;
            font-size: 1rem;
            box-sizing: border-box;
        }

        .search-area button {
            padding: 6px 12px;
            font-size: 1rem;
            cursor: pointer;
        }

        #sortableList {
            list-style: none;
            padding: 0;
            margin: 0;
            border: 1px solid #ccc;
            max-width: 300px;
        }

        .sortable-item {
            padding: 10px 15px;
            border-bottom: 1px solid #ccc;
            background-color: #fafafa;
            cursor: grab;
            user-select: none;
        }

        .sortable-item:last-child {
            border-bottom: none;
        }

        .sortable-item:active {
            cursor: grabbing;
        }
        
        .content-right {
            display: flex;
            flex-direction: column;
            gap: 10px;
            height: 100%;
            box-sizing: border-box;
        }

        .content-right-top, .content-right-bottom {
            flex: 1;
            border: 1px solid #ccc;
            padding: 15px;
            box-sizing: border-box;
        }
        
        #priceEdit {
            text-align: right; 
            margin-bottom: 10px;
        }
        
        #priceEdit button {
            padding: 6px 12px;
            font-size: 1rem;
            cursor: pointer;
        }
        
        .totalPrice {
            margin-top: 15px; 
            font-weight: bold;  
            padding-left: 10px;
        }
        
        .memo {
            display: flex;
            flex-direction: column;
        }
        
    </style>
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
        location.href = 'mylist.jsp';
    } else if (deleteResult === 'false') {
        alert('삭제에 실패했습니다.');
    }

    document.getElementById('deleteBtn').addEventListener('click', function () {
        if (confirm('정말 삭제하시겠습니까?')) {
            location.href = 'deleteTodo.do';
        }
    });

    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>

</body>
</html>
