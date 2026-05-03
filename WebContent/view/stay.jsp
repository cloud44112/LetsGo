<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/stay.css?v=3">
<link rel="stylesheet" type="text/css" href="/LetsGo/common/floating-cart.css">
</head>
<body>

<jsp:include page="header.jsp" />

<form action="/LetsGo/controller" method="GET">
    <input type="hidden" name="cmd" value="searchPlace" />
    <input type="hidden" name="placeType" value="STAY" />
	<div class="layout-wrapper">
		<aside class="sidebar">
			<div>
				<ul>
					<li><details>
							<summary>호텔</summary>
							<ul>
								<li><label><input type="radio" name="category" value="AC010100"
	                                onchange="this.form.submit()"
	                                ${param.category == 'AC010100' ? 'checked' : ''}> 호텔</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>콘도</summary>
							<ul>
								<li><label><input type="radio" name="water-leisure">
										콘도</label></li>
								<li><label><input type="radio" name="water-leisure">
										레지던스</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>펜션/민박</summary>
							<ul>
								<li><label><input type="radio" name="air-leisure">
										펜션</label></li>
								<li><label><input type="radio" name="category" value="AC030200"
						            onchange="this.form.submit()"
						            ${param.category == 'AC030200' ? 'checked' : ''}> 한옥스테이</label></li>
								<li><label><input type="radio" name="air-leisure">
										농어촌</label></li>
								<li><label><input type="radio" name="air-leisure">
										민박</label></li>
								<li><label><input type="radio" name="air-leisure">
										홈스테이</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>모텔</summary>
							<ul>
								<li><label><input type="radio"
										name="complex-leisure"> 모텔</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>캠핑</summary>
							<ul>
								<li><label><input type="radio"
										name="complex-leisure"> 일반야영장</label></li>
								<li><label><input type="radio"
										name="complex-leisure"> 오토캠핑장</label></li>
								<li><label><input type="radio"
										name="complex-leisure"> 카라반</label></li>
								<li><label><input type="radio"
										name="complex-leisure"> 글램핑장</label></li>
							</ul>
						</details></li>
				</ul>
			</div>
		</aside>
	
		<main>
	
		<div class="content-container">
			<div class="content-left">
				총 ${totalCount}개의 항목
				<div class="search-area">
					<input type="text" name="keyword"
                           placeholder="장소 이름이나 지역을 검색하세요"
                           value="${param.keyword}" />
                    <button type="submit">검색하기</button>
					<div class="sort-area">
						<select name="sortOrder" onchange="this.form.submit()">
							<option value="name"    ${param.sortOrder != 'popular' ? 'selected' : ''}>이름순</option>
							<option value="popular"  ${param.sortOrder == 'popular' ? 'selected' : ''}>인기순</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	
		<div class="container">
	    <c:forEach var="place" items="${stayPlaceList}">
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
				        data-place-type="STAY">
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
			<button type="button" id="leisere"> <<= 레저스포츠</button>
		    <button type="button" id="restaurant"> <= 음식점 </button>
		</div>
		</main>
	</div>
</form>

<script type="text/javascript">

	window.onload = function(){
		document.querySelector("#leisere").onclick = function(){
			window.location.href = '/LetsGo/controller?cmd=leisureUI';
		}
		
		document.querySelector("#restaurant").onclick = function(){
			window.location.href = '/LetsGo/controller?cmd=restaurantUI';
		}
	}
	
	// (1) 모든 좋아요 버튼을 선택
	let likeBtns = document.querySelectorAll(".like-btn");

	for (let i = 0; i < likeBtns.length; i++) {
		likeBtns[i].onclick = function () {

			let placeId = this.getAttribute("data-place-id");
			let placeType = this.getAttribute("data-place-type");
			if (placeType == null || placeType == "") {
				placeType = "STAY";
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