<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />
<jsp:include page="MyScheduleRoute.jsp" />

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