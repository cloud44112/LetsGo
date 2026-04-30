<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/restaurant.css">
<link rel="stylesheet" type="text/css" href="/LetsGo/common/floating-cart.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="layout-wrapper">
	<aside class="sidebar">
		<div>
			<button>한식</button>
		</div>
		<div>
			<button>중식</button>
		</div>
		<div>
			<button>일식</button>
		</div>
		<div>
			<button>양식</button>
		</div>
	</aside>

	<main> 
	<div class="content-container">
		<div class="content-left">
			<div class="search-area">
				<input type="text" placeholder="장소 이름이나 지역을 검색하세요" />
				<button type="button">검색하기</button>
				<div class="sort-area">
					<select name="sortOrder">
						<option value="distance">거리순</option>
						<option value="like">인기순</option>
					</select>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
    <c:forEach var="place" items="${restaurantPlaceList}">
        <figure class="figure">
            <c:choose>
                <c:when test="${not empty place.firstImage}">
                    <img src="${place.firstImage}" alt="${place.title}" class="box-placeholder" />
                </c:when>
                <c:otherwise>
                    <a href="#" class="box-placeholder">이미지</a>
                </c:otherwise>
            </c:choose>
            <figcaption class="figure-caption">${place.title}</figcaption>
            ${place.addr1}
            <form method="post" action="/LetsGo/controller?cmd=placeLikeAction">
                <input type="hidden" name="placeId" value="${place.placeId}" />
                <input type="hidden" name="redirectCmd" value="restaurantUI" />
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
	
	<div class = "button-container">
		<button id="leisere"> <= 레저스포츠 </button>
	    <button id="stay"> 숙박 => </button>
	</div>
	</main>
</div>

<script type="text/javascript">

	window.onload = function(){
		document.querySelector("#leisere").onclick = function(){
			window.location.href = '/LetsGo/controller?cmd=leisureUI';
		}
		
		document.querySelector("#stay").onclick = function(){
			window.location.href = '/LetsGo/controller?cmd=stayUI';
		}
	}
	
</script>

<script src="/LetsGo/common/floating-cart.js"></script>

</body>
</html>