<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LetsGo/view/css/myScheduleList.css">
</head>
<body>
	<jsp:include page="myScheduleListSideBar.jsp" />
	<jsp:include page="header.jsp" />

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
			<div class="edit-area">
				<button>수정하기</button>
			</div>
			<div class="container">
			<c:forEach var="item" items="${myScheduleList}">
				<figure class="figure">
				<div>
					${item.myScheduleTitle}
					<c:if test="${item.isShared == 1}">👥</c:if>
				</div>
				<a href="controller?cmd=myScheduleRouteManageUI&myScheduleId=${item.myScheduleId}" class="box-placeholder"> <img src="${item.firstImage}"
					alt="일정 이미지" class="box-placeholder">
				</a> <figcaption class="figure-caption">${item.placeTitle}</figcaption>
				${fn:substring(item.startAt, 0, 10)} 📍${fn:substring(item.addr1, 0, 10)}
				</figure>
			</c:forEach>
			</div>
		</div>
	</div>
	
	<div></div>

	</main>


</body>
</html>