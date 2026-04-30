<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/leisure.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="layout-wrapper"> 
	<aside class="sidebar">
		<div>
			<ul>
				<li><details>
						<summary>육상레저스포츠</summary>
						<ul>
							<li><label><input type="radio" name="land-leisure">
									인라인</label></li>
							<li><label><input type="radio" name="land-leisure">
									자전거</label></li>
							<li><label><input type="radio" name="land-leisure">
									카트</label></li>
							<li><label><input type="radio" name="land-leisure">
									골프</label></li>
							<li><label><input type="radio" name="land-leisure">
									경마</label></li>
							<li><label><input type="radio" name="land-leisure">
									승마</label></li>
							<li><label><input type="radio" name="land-leisure">
									스키/스노보드</label></li>
							<li><label><input type="radio" name="land-leisure">
									스케이트</label></li>
							<li><label><input type="radio" name="land-leisure">
									썰매장</label></li>
							<li><label><input type="radio" name="land-leisure">
									사격장</label></li>
							<li><label><input type="radio" name="land-leisure">
									서바이벌게임</label></li>
							<li><label><input type="radio" name="land-leisure">
									번지점프</label></li>
							<li><label><input type="radio" name="land-leisure">
									오프로드</label></li>
							<li><label><input type="radio" name="land-leisure">
									기타육상레저스포츠</label></li>
						</ul>
					</details></li>

				<li><details>
						<summary>수상레저스포츠</summary>
						<ul>
							<li><label><input type="radio" name="water-leisure">
									윈드서핑/제트스키</label></li>
							<li><label><input type="radio" name="water-leisure">
									카약/카누</label></li>
							<li><label><input type="radio" name="water-leisure">
									요트</label></li>
							<li><label><input type="radio" name="water-leisure">
									스노쿨링/스킨스쿠버다이빙</label></li>
							<li><label><input type="radio" name="water-leisure">
									낚시</label></li>
							<li><label><input type="radio" name="water-leisure">
									수영</label></li>
							<li><label><input type="radio" name="water-leisure">
									수상오토바이</label></li>
							<li><label><input type="radio" name="water-leisure">
									수상자전거</label></li>
							<li><label><input type="radio" name="water-leisure">
									워터슬레드</label></li>
							<li><label><input type="radio" name="water-leisure">
									패러세일</label></li>
							<li><label><input type="radio" name="water-leisure">
									기타수상레저스포츠</label></li>
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
							<li><label><input type="radio"
									name="complex-leisure"> 복합레저스포츠</label></li>
						</ul>
					</details></li>
			</ul>
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
			<figcaption class="figure-caption">금원수원 수영장</figcaption>
			경기 안양
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">덕진 수영장</figcaption>
			서울 동구
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">덕주 수영장</figcaption>
			대구 경북
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">박태환 수영장</figcaption>
			인천 미추홀구
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">한강시민 공원 수영장</figcaption>
			서울 중구
			<button>담기</button>
		</figure>
		<figure class="figure">
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">EVO 수영장</figcaption>
			서울 중구
			<button>담기</button>
		</figure>
	</div>

	<div class="button-container">
		<button id="restaurant">음식점</button>
		<button id="stay">숙박</button>
		<button>내가 담은 방문지</button>
	</div>
	</main>
</div>

<script type="text/javascript">
	
		window.onload = function(){
			document.querySelector("#restaurant").onclick = function(){
				window.location.href = '/LetsGo/controller?cmd=restaurantUI';
			}
			
			document.querySelector("#stay").onclick = function(){
				window.location.href = '/LetsGo/controller?cmd=stayUI';
			}
		}
	
</script>

</body>
</html>