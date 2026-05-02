<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/leisure.css?v=3">
<link rel="stylesheet" type="text/css" href="/LetsGo/common/floating-cart.css">
</head>
<body>

<jsp:include page="header.jsp" />
<form action="/LetsGo/controller" method="GET">
	<input type="hidden" name="cmd" value="searchPlace" />
    <input type="hidden" name="placeType" value="LEISURE" />
    
	<div class="layout-wrapper"> 
		<aside class="sidebar">
			<div>
				<ul>
					<li><details>
							<summary>육상레저스포츠</summary>
							<ul>
								<li><label><input type="radio" name="category" value="LS010200" onchange="this.form.submit()" ${param.category == 'LS010200' ? 'checked' : ''}> 자전거 (둘레길)</label></li>
								<li><label><input type="radio" name="category" value="LS010900" onchange="this.form.submit()" ${param.category == 'LS010900' ? 'checked' : ''}> 스케이트</label></li>
								<li><label><input type="radio" name="category" value="LS011200" onchange="this.form.submit()" ${param.category == 'LS011200' ? 'checked' : ''}> 사격장</label></li>
								<li><label><input type="radio" name="category" value="LS011900" onchange="this.form.submit()" ${param.category == 'LS011900' ? 'checked' : ''}> 산책/둘레길</label></li>
								<li><label><input type="radio" name="category" value="VE100100" onchange="this.form.submit()" ${param.category == 'VE100100' ? 'checked' : ''}> 종합체육시설</label></li>
								<li><label><input type="radio" name="category" value="VE100200" onchange="this.form.submit()" ${param.category == 'VE100200' ? 'checked' : ''}> 수련시설</label></li>
							</ul>
						</details></li>
						
					<li><details>
							<summary>수상레저스포츠</summary>
							<ul>
								<li><label><input type="radio" name="category" value="LS020100" onchange="this.form.submit()" ${param.category == 'LS020100' ? 'checked' : ''}> 윈드서핑/수상스키</label></li>
								<li><label><input type="radio" name="category" value="LS020300" onchange="this.form.submit()" ${param.category == 'LS020300' ? 'checked' : ''}> 요트</label></li>
								<li><label><input type="radio" name="category" value="LS020700" onchange="this.form.submit()" ${param.category == 'LS020700' ? 'checked' : ''}> 수영</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>항공레저스포츠</summary>
							<ul>
								<li><label><input type="radio" name="air-leisure">
										스카이다이빙</label></li>
								<li><label><input type="radio" name="air-leisure">
										초경량비행</label></li>
								<li><label><input type="radio" name="air-leisure">
										헹글라이딩/패러글라이딩</label></li>
								<li><label><input type="radio" name="air-leisure">
										열기구</label></li>
								<li><label><input type="radio" name="air-leisure">
										무인비행장치(드론)</label></li>
								<li><label><input type="radio" name="air-leisure">
										기타항공레저스포츠</label></li>
							</ul>
						</details></li>
	
					<li><details>
							<summary>복합레저스포츠</summary>
							<ul>
								<li><label><input type="radio" name="category" value="VE120200" onchange="this.form.submit()" ${param.category == 'VE120200' ? 'checked' : ''}> 복합레저(카지노 등)</label></li>
							</ul>
						</details></li>
				</ul>
			</div>
		</aside>
	
		<main> 
		<div class="content-container">
			<h3>총 ${totalCount}개의 항목 </h3>
			<div class="content-left">
				<div class="search-area">
					<input type="text" name="keyword" placeholder="장소 이름이나 지역을 검색하세요" 
					       value="${param.keyword}" />
					<button type="submit">검색하기</button>
	
					<div class="sort-area">
						<select name="sortOrder" onchange="this.form.submit()">
							<option value="distance">거리순</option>
							<option value="popular"  ${param.sortOrder == 'popular'  ? 'selected' : ''}>인기순</option>
						</select>
					</div>
	
				</div>
			</div>
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
	
	            <button type="button" class="like-btn"
						data-place-id="${place.placeId}"
						data-place-type="LEISURE">
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
	
		<div class="button-container">
			<button type="button" id="restaurant"> 음식점 => </button>
			<button type="button" id="stay"> 숙박 =>> </button>
		</div>
		</main>
	</div>
</form>

<script type="text/javascript">
	
	window.onload = function(){
		document.querySelector("#restaurant").onclick = function(){
			window.location.href = '/LetsGo/controller?cmd=restaurantUI';
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
				placeType = "LEISURE";
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