<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/restaurant.css?v=2">
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
			<button type="button" class="like-btn"
			        data-place-id="${place.placeId}"
			        data-place-type="RESTAURANT">
			        <h1>❤️</h1>
			</button>
			<div class="like-count" id="likeCount-${place.placeId}">좋아요: ${place.likeCount}</div>
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
	
	// (1) 모든 좋아요 버튼을 선택
	let likeBtns = document.querySelectorAll(".like-btn");

	for (let i = 0; i < likeBtns.length; i++) {
		likeBtns[i].onclick = function () {

			let placeId = this.getAttribute("data-place-id");
			let placeType = this.getAttribute("data-place-type");
			if (placeType == null || placeType == "") {
				placeType = "RESTAURANT";
			}

			let xhr = new XMLHttpRequest();

			xhr.onreadystatechange = function () {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						let parsed = JSON.parse(xhr.responseText);

						if (parsed.result == "success") {
							document.getElementById("likeCount-" + placeId)
								.innerText = "좋아요: " + parsed.likeCount;
						} else {
							alert("로그인이 필요합니다.");
						}
					}
				}
			};

			xhr.open("GET",
				"${pageContext.request.contextPath}/placeLikeAjax?placeId=" + placeId + "&placeType=" + placeType,
				true);

			xhr.send(null);
		};
	}
	
</script>

<div id="session-cart-data" style="display: none;">
	<c:forEach var="cartPlace" items="${sessionScope.placeCartList}">
		<div class="session-cart-item"
			data-place-id="<c:out value='${cartPlace.placeId}' />"
			data-place-title="<c:out value='${cartPlace.title}' />"
			data-place-type="<c:out value='${cartPlace.placeType}' />"></div>
	</c:forEach>
</div>
<script src="/LetsGo/common/floating-cart.js"></script>

</body>
</html>