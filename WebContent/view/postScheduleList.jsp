<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/LetsGo/view/css/myScheduleList.css">
</head>
<body>
<jsp:include page="postScheduleListSideBar.jsp" />
<jsp:include page="header.jsp" />

<main>
    <div class="content-container">
        <div class="content-left">
            <div class="search-area">
                <input type="text" placeholder="장소 이름이나 일정 이름을 검색하세요" />
                <button type="button">검색하기</button>
                <div class="sort-area">
                    <select name="sortOrder">
                        <option value="latest">날짜순</option>
                        <option value="name">이름순</option>
                        <option value="name">좋아요순</option>
                        <option value="name">조회순</option>
                    </select>
                </div>
            </div>
           	<div class="edit-area">
				<button><a href="controller?cmd=myScheduleListUI">내 일정 공유</a></button>
			</div>
        </div> </div> <div class="container">
        <c:forEach var="postSchedule" items="${postScheduleList}">
            <figure class="figure">
                <div>
                    ${postSchedule.title}
                </div>

                <a href="controller?cmd=postScheduleRouteManageUI&postId=${postSchedule.postId}" class="box-placeholder">
  					  <img src="${postSchedule.firstImage}" alt="일정 이미지" class="box-placeholder"></a>
                
                <figcaption class="figure-caption">${postSchedule.placeTitle}</figcaption>

                <div>
                	<span type = "button" class="like-btn" data-postId="${postSchedule.postId}">❤️ 좋아요 : ${postSchedule.likeCount}</span>
                	<span>&ensp;</span>
                    <span>조회수 : ${postSchedule.viewCount}</span>	
                    
                </div>
                
                <div>
                	<span>
                		📍 ${fn:substring(postSchedule.addr1, 0, 10)}
                	</span>
                    <span>
                    	👤 ${postSchedule.isAnonymous == 1 ? '익명' : postSchedule.userName}
                    </span>
                </div>
            </figure>
        </c:forEach>
    </div>
</main>
	<script type="text/javascript">
	let likeButtons = document.querySelectorAll(".like-btn");
	let idEvent = function() {
        let clickedBtn; 
        clickedBtn = this; 
        let postId = this.getAttribute("data-postId"); 
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
            	let response = JSON.parse(xhr.responseText);
                if (response.result === true) {
                	let dbCount = parseInt(response.count);
                	let currentCount = parseInt(clickedBtn.innerText.replace(/[^0-9]/g, "")) || 0;
                	if(dbCount > currentCount) {
                		clickedBtn.innerText = "❤️ 좋아요 : " + dbCount;  
                	}   
                } else {
                	alert("좋아요 처리에 실패했습니다.");
           		}
        	}
      	};
        xhr.open("GET", "controller?cmd=postScheduleLike&postId=" + postId, true);
        xhr.send(null);
    };
    for (let i = 0; i < likeButtons.length; i++) {
        likeButtons[i].onclick = idEvent;
    }

	</script>
</body>
</html>