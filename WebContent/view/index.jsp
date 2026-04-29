<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8" />
<link rel="stylesheet" href="common/floating-cart.css" />
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/index.css">
<style>
.like-btn {
	background: none;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 0;
	transition: transform 0.12s ease, filter 0.12s ease;
}

.like-btn:hover {
	transform: scale(1.12);
	filter: brightness(1.05);
}

.like-btn:active {
	transform: scale(0.9);
}
</style>
</head>

<body>
	<jsp:include page="header.jsp" />

	<h1>추천 레저스포츠 리스트</h1>


	<div class="sort-area">
		<select name="sortOrder">
			<option value="distance">거리순</option>
			<option value="like">인기순</option>
		</select>
		<li><a href="">위치서비스</a></li>
	</div>


	<div class="container">
		<c:forEach var="place" items="${leisurePlaceList}">
			<figure class="figure">
				<c:choose>
					<c:when test="${not empty place.firstImage}">
						<img src="${place.firstImage}" alt="${place.title}"
							class="box-placeholder" />
					</c:when>
					<c:otherwise>
						<a href="#" class="box-placeholder">이미지</a>
					</c:otherwise>
				</c:choose>
				<figcaption class="figure-caption">${place.title}</figcaption>
				${place.addr1}
				<form method="post" action="<%=request.getContextPath()%>/controller?cmd=placeLikeAction">
					<input type="hidden" name="placeId" value="${place.placeId}" />
					<button type="submit" class="like-btn">❤️</button>
					<span>좋아요: ${place.likeCount}</span>
				</form>
				<button type="button" class="add-to-cart-btn"
					data-place-id="${place.placeId}"
					data-place-title="${place.title}"
					data-place-type="${place.placeType}">담기</button>
			</figure>
		</c:forEach>
	</div>


	</main>
	<script src="common/floating-cart.js"></script>

</body>
</html>