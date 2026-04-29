<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />


<main>
    <header>
        <div class="logo">레스고!</div>
        <ul class="menu" style="list-style:none; display:flex; padding:0; margin:0;">
			<li style="margin-right: 20px;"><a href="index.html">홈</a></li>
			<li style="margin-right: 20px;"><a href="leisere.html">플레이스 조회</a></li>
			<li style="margin-right: 20px;"><a href="schedulepostAll.html">일정게시판</a></li>
			<li style="margin-right: 20px;"><a href="#">내일정</a></li>
            <li>로그인</li>
        </ul>
    </header>

    <div class="content-container">
        <div class="content-left">
            <ul id="sortableList">
                <li class="sortable-item">1번 요소</li>
                <li class="sortable-item">2번 요소</li>
                <li class="sortable-item">3번 요소</li>
                <li class="sortable-item">4번 요소</li>
            </ul>
			<div class="content-left-bottom">
			  <button type="button">♥ 좋아요!</button>
			  <span class="like-count">♥ + 222</span>
			</div>
        </div>
        <div class="divider"></div>
		<div class="content-right">
		    <table border="1" align="center">
		        <tbody>
		            <tr>
		                <th colspan="2">놀거리 비용</th>
		                <td>20,000</td>
		            </tr>
		
		            <tr>
		                <th rowspan="2">식비</th>
		                <td>빽돈 망포점</td>
		                <td>10,000</td>
		            </tr>
		
		            <tr>
		                <td>포크너 광교아븐뉴 프라점</td>
		                <td>10,000</td>
		            </tr>
		
		            <tr>
		                <th colspan="2">숙박비</th>
		                <td>70,000</td>
		            </tr>
		        </tbody>
		    </table>
		    <div class="totalPrice">
		    	총계 : 110,000 원
            </div>
		</div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>

</body></html>