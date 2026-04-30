<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/restaurant.css">
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
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">삼미락</figcaption>
			❤:121 수원
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">정희</figcaption>
			❤:111 수원
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">무월</figcaption>
			❤:111 수원
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">다선 칼국수</figcaption>
			❤:111 수원
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">이교수 한정식</figcaption>
			❤:111 수원
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">약수터</figcaption>
			❤:111 수원
			<button>담기</button>
		</figure>
	</div>
	
	<div class = "button-container">
		<button id="leisere">레저스포츠</button>
	    <button id="stay">숙박</button>
	    <button>내가 담은 방문지</button>
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

</body>
</html>