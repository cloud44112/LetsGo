<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- <link rel = "stylesheet" href ="letsgo.css"> -->
<meta charset="UTF-8" />
<link rel="stylesheet" href="common/floating-cart.css" />
<link rel="stylesheet" type="text/css" href="css/mySchedule.css">

</head>
<body>

	<main> <header>
		<div class="logo">레스고!</div>
		<ul class="menu"
			style="list-style: none; display: flex; padding: 0; margin: 0;">
			<li style="margin-right: 20px;"><a href="index.html">홈</a></li>
			<li style="margin-right: 20px;"><a href="leisere.html">플레이스 조회</a></li>
			<li style="margin-right: 20px;"><a href="schedulepostAll.html">일정게시판</a></li>
			<li style="margin-right: 20px;"><a href="#">내일정</a></li>
			<li><a href ="login.html">로그인</a></li>
		</ul>
	</header>
	<h1>추천 레저스포츠 리스트</h1>


	<div class="sort-area">
		<select name="sortOrder">
			<option value="distance">거리순</option>
			<option value="like">인기순</option>
		</select>
		<li><a href="">위치서비스</a></li>
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


	</main>
 <script src="common/floating-cart.js"></script>

</body>
</html>