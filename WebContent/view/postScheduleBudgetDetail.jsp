<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />
<main>
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

</body>
</html>