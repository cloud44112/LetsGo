<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-left">
    <div class="schedule-header">
        <span id="scheduleTitle" class="title-input">${scheduleTitle}</span>
    </div>
    <ul id="sortableList">
        <c:forEach var="route" items="${ScheduleRoute}">
            <li class="sortable-item" data-visit-id="${route.visitId}">
                ${route.visitOrder}. ${route.title}              
            </li>
        </c:forEach>
    </ul>
        <div class="content-left-bottom">

            <c:forEach var="postSchedule" items="${postScheduleList}">
            <figure class="figure">
                <div>
                    ${postSchedule.title}
                </div>

                <a href="controller?cmd=postScheduleRouteManageUI&postId=${postSchedule.postId}" class="box-placeholder">
  					  <img src="${postSchedule.firstImage}" alt="일정 이미지" class="box-placeholder">
				</a>
                
                <figcaption class="figure-caption">${postSchedule.placeTitle}</figcaption>

                <div>
                	<span type = "button" class="like-btn" data-postId="${postSchedule.postId}">❤️ ${postSchedule.likeCount}</span>
                    <span>조회수: ${postSchedule.viewCount}</span>	
                    <span>👤 ${postSchedule.isAnonymous == 1 ? '익명' : postSchedule.userName} 님</span>
                </div>
                
                <div>
                    📍 ${postSchedule.addr1}
                </div>
            </figure>
        </c:forEach>
    </div>
</div>
