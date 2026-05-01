<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-left">
    <div class="schedule-header">
        <span id="scheduleTitleDisplay" class="title-input">${scheduleTitle}</span>
    </div>
    <ul id="sortableList">
        <c:forEach var="route" items="${ScheduleRoute}">
            <li class="sortable-item" data-visit-id="${route.visitId}">
                ${route.visitOrder}. ${route.title}              
            </li>
        </c:forEach>
    </ul>
</div>
