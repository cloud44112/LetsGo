<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="layout-wrapper">
    <jsp:include page="mySchduleSideBar.jsp" />
    <main>
        <div class="content-container">
            <jsp:include page="MyScheduleRoute.jsp" />
            <div class="divider"></div>
            <div class="content-right">
                <div class="content-right-top">
                    <div class="map-area">
                        <c:choose>
                            <c:when test="${not empty MapSchedule}">
                                <c:set var="staticMapUrl" value="https://maps.apigw.ntruss.com/map-static/v2/raster-cors?w=800&h=500"/>
                                <c:forEach var="route" items="${MapSchedule}">
                                    <c:if test="${not empty route.mapX and not empty route.mapY}">
                                        <c:set var="staticMapUrl" value="${staticMapUrl}&markers=type:n|size:mid|color:Blue|label:${route.visitOrder}|pos:${route.mapX}%20${route.mapY}"/>
                                    </c:if>
                                </c:forEach>
                                <c:set var="staticMapUrl" value="${staticMapUrl}&X-NCP-APIGW-API-KEY-ID=xvyp5v7zoj"/>
                                <img class="static-map" src="${staticMapUrl}" alt="경로 지도">
                            </c:when>
                            <c:otherwise>
                                <div class="map-empty">추가된 장소가 없습니다.</div>
                            </c:otherwise>
                        </c:choose>
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
</div>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>
</body>
</html>
