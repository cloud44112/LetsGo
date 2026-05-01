<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
</head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">

<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />

<main>
    <div class="content-container">
        <jsp:include page="MyScheduleRoute.jsp" />
        <div class="divider"></div>
        <div class="content-right">
            <div class="content-right-top">
                <div class="map-area">
                    <div id="map"></div>
                </div>
            </div>
            <div class="content-right-bottom">
                <c:forEach var="route" items="${ScheduleRoute}">
                    ${route.visitOrder}. ${route.title}<br/>
                </c:forEach>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=nw9515cuj4"></script>
<script>
    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });

    window.onload = function () {
        var map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.5665, 126.9780),
            zoom: 10
        });
    };
</script>
</body>
