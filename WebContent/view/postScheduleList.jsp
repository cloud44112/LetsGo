<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 1. JSTL 선언 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    </select>
                </div>
            </div>
        </div> </div> <div class="container">
        <c:forEach var="item" items="${postScheduleList}">
            <figure class="figure">
                <div>
                    ${item.title}
                </div>

                <a href="controller?cmd=postScheduleRouteManageUI&postId=${item.postId}" class="box-placeholder">
  					  <img src="${item.firstImage}" alt="일정 이미지" class="box-placeholder">
				</a>
                
                <figcaption class="figure-caption">${item.placeTitle}</figcaption>

                <div>
                    <span>❤️ ${item.likeCount}</span>
                    <span>조회수: ${item.viewCount}</span>	
                    <span>👤 ${item.isAnonymous == 1 ? '익명' : item.userName} 님</span>
                </div>
                
                <div>
                    📍 ${item.addr1}
                </div>
            </figure>
        </c:forEach>
    </div>
</main>
</body>
</html>