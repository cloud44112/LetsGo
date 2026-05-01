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
        <span type = "button" class="like-btn" data-postId="${postId}">❤️좋아요!</span>
        <span type = "text" class="like-Count" data-likeCount="${count}">❤️ + ${count}</span>
    </div>
</div>
	<script type="text/javascript">
	
	let likeBtn = document.querySelector(".like-btn");
	let likeCount = document.querySelector(".like-Count");
	
	let idEvent = function() {
        let postId = likeBtn.getAttribute("data-postId"); 
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
            	let response = JSON.parse(xhr.responseText);
                if (response.result === true) {
                	let dbCount = parseInt(response.count);
                	let currentCount = parseInt(likeCount.innerText.replace(/[^0-9]/g, "")) || 0;
                	if(dbCount > currentCount) {
                		likeCount.innerText = "❤️ +  " + dbCount;  
                	}   
                } else {
                	alert("좋아요 처리에 실패했습니다.");
           		}
        	}
      	};
        xhr.open("GET", "controller?cmd=postScheduleLike&postId=" + postId, true);
        xhr.send(null);
    };
    likeBtn.onclick = idEvent;
	</script>
