<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
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
            <div class="content-right-top">
            	<div class="map-area">
			        <div id="map"></div>
			    </div>
            </div>
            <div class="content-right-bottom">
                1. 금원 수원 수영장</br>

                2. 빽돈 망포점</br>

                3. 포크너 광교아븐뉴 프라점</br>

                4. 앰배서더 수원</br>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nw9515cuj4"></script>

<script>
    
    window.onload = function () {
        var map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.5665, 126.9780),
            zoom: 10
        });
    };
    
</script>
</body>