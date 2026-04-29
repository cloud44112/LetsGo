<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LetsGo/view/css/myScheduleList.css">
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
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
   			<div class="edit-area">
					<button>수정하기</button>
			</div>
		</div>
	</div>

	<div class="container">
		<c:forEach var="item" items="${myScheduleList}">
		<figure class="figure">
		<div>
			${item.myScheduleTitle}
			<c:if test="${item.isShared == 1}">👥</c:if>
		</div>
		<a href="#" class="box-placeholder"> <img src="${item.firstImage}"
			alt="일정 이미지" class="box-placeholder">
		</a> <figcaption class="figure-caption">${item.placeTitle}</figcaption>
		<div>${item.startAt} 📍${item.addr1}</div>
		</figure>
	</c:forEach>
	</div>

</main>
</body>
</html>