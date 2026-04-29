<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<main>
    <div class="content-container">
        <div class="content-left">
            <ul id="sortableList">
                <c:forEach var="route" items="${ScheduleRoute}">
                    <li class="sortable-item" data-visit-id="${route.visitId}">
                        ${route.visitOrder}. ${route.title}
                    </li>
                </c:forEach>
            </ul>		

            <div class="content-left-bottom">
                <button type="button">저장하기!</button>
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
                <c:forEach var="route" items="${ScheduleRoute}">
                    ${route.visitOrder}. ${route.title}<br/>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
</html>
