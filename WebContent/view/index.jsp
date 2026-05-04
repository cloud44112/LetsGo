<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8" />
<link rel="stylesheet" href="common/floating-cart.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/index.css?v=2">
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
		<label>
			<select id="sortOrderSelect" name="sortOrder">
				<option value="distance"
					<c:if test="${so ne 'like'}">selected</c:if>>전체</option>
				<option value="like" <c:if test="${so eq 'like'}">selected</c:if>>좋아요순</option>
			</select>
		</label>
		
	</div>

	<div class="container" id="leisurePlaceContainer">
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
(function () {
	var apiBase = window.location.origin + "${pageContext.request.contextPath}";

	function renderPlaces(list) {
		var container = document.getElementById("leisurePlaceContainer");
		if (!container) {
			return;
		}
		while (container.firstChild) {
			container.removeChild(container.firstChild);
		}
		var frag = document.createDocumentFragment();
		for (var i = 0; i < list.length; i++) {
			var p = list[i];
			var fig = document.createElement("figure");
			fig.className = "figure";
			if (p.firstImage && String(p.firstImage).length > 0) {
				var img = document.createElement("img");
				img.src = p.firstImage;
				img.alt = p.title || "";
				img.className = "box-placeholder";
				fig.appendChild(img);
			} else {
				var a = document.createElement("a");
				a.href = "#";
				a.className = "box-placeholder";
				a.textContent = "이미지";
				fig.appendChild(a);
			}
			var cap = document.createElement("figcaption");
			cap.className = "figure-caption";
			cap.textContent = p.title || "";
			fig.appendChild(cap);
			fig.appendChild(document.createTextNode(p.addr1 || ""));
			
			var likeBtn = document.createElement("button");
			likeBtn.type = "button";
			likeBtn.className = "like-btn";
			likeBtn.setAttribute("data-place-id", p.placeId);
			likeBtn.setAttribute("data-place-type", p.placeType || "LEISURE");
			
			var h1 = document.createElement("h1");
			h1.textContent = "❤️";
			likeBtn.appendChild(h1);
			fig.appendChild(likeBtn);
			
			var likeDiv = document.createElement("div");
			likeDiv.className = "like-count";
			likeDiv.id = "likeCount-" + p.placeId;
			likeDiv.textContent = "좋아요: " + p.likeCount;
			fig.appendChild(likeDiv);
			
			var cartBtn = document.createElement("button");
			cartBtn.type = "button";
			cartBtn.className = "add-to-cart-btn";
			cartBtn.setAttribute("data-place-id", p.placeId);
			cartBtn.setAttribute("data-place-title", p.title || "");
			cartBtn.setAttribute("data-place-type", p.placeType || "LEISURE");
			cartBtn.textContent = "담기";
			fig.appendChild(cartBtn);
			frag.appendChild(fig);
		}
		container.appendChild(frag);
	}

	function requestSort(sortOrder) {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			if (xhr.readyState !== 4) {
				return;
			}
			if (xhr.status !== 200) {
				alert("목록 정렬 요청 실패 (HTTP " + xhr.status + ")");
				return;
			}
			var data;
			try {
				data = JSON.parse(xhr.responseText);
			} catch (e) {
				alert("JSON이 아닙니다.");
				return;
			}
			if (data && data.error === true) {
				alert(data.message || "목록을 불러오지 못했습니다.");
				return;
			}
			if (!Array.isArray(data)) {
				alert("목록 형식 오류");
				return;
			}
			renderPlaces(data);
		};
		xhr.open("GET", apiBase + "/leisureListAjax?sortOrder=" + encodeURIComponent(sortOrder), true);
		xhr.send(null);
	}

	document.getElementById("leisurePlaceContainer").addEventListener("click", function (ev) {
		var btn = ev.target.closest(".like-btn");
		if (!btn) {
			return;
		}
		var placeId = btn.getAttribute("data-place-id");
		var placeType = btn.getAttribute("data-place-type");
		if (placeType == null || placeType === "") {
			placeType = "LEISURE";
		}
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			if (xhr.readyState !== 4) {
				return;
			}
			if (xhr.status === 200) {
				var parsed;
				try {
					parsed = JSON.parse(xhr.responseText);
				} catch (e) {
					alert("좋아요 응답이 JSON이 아닙니다.");
					return;
				}
				if (parsed.result === "success") {
					var el = document.getElementById("likeCount-" + placeId);
					if (el) {
						el.innerText = "좋아요: " + parsed.likeCount;
					}
				} else {
					alert("로그인이 필요합니다.");
				}
			} else {
				alert("좋아요 요청 실패 (HTTP " + xhr.status + ").");
			}
		};
		xhr.open("GET", apiBase + "/placeLikeAjax?placeId=" + encodeURIComponent(placeId) + "&placeType=" + encodeURIComponent(placeType), true);
		xhr.send(null);
	});

	var sortSel = document.getElementById("sortOrderSelect");
	if (sortSel) {
		sortSel.addEventListener("change", function () {
			requestSort(sortSel.value);
		});
	}
})();
</script>
</body>
</html>