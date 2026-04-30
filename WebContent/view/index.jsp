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
	<c:set var="so" value="${empty sortOrder ? 'distance' : sortOrder}" />
	<jsp:include page="header.jsp" />

	<h1>추천 레저스포츠 리스트</h1>

	<div class="sort-area">
		<form method="get"
			action="${pageContext.request.contextPath}/controller">
			<select name="sortOrder" onchange="this.form.submit()">
				<option value="distance"
					<c:if test="${so ne 'like'}">selected</c:if>>거리순</option>
				<option value="like" <c:if test="${so eq 'like'}">selected</c:if>>좋아요순</option>
			</select>
		</form>
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


	</main>
	<div id="session-cart-data" style="display: none;">
		<c:forEach var="cartPlace" items="${sessionScope.placeCartList}">
			<div class="session-cart-item"
				data-place-id="<c:out value='${cartPlace.placeId}' />"
				data-place-title="<c:out value='${cartPlace.title}' />"
				data-place-type="<c:out value='${cartPlace.placeType}' />"></div>
		</c:forEach>
	</div>
	<script src="common/floating-cart.js"></script>

	<script type="text/javascript">
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



</body>
</html>