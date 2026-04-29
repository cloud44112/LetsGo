<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />
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

	<div class="content-container">
		<div class="content-left">
			<div class="search-area">
				<input type="text" placeholder="장소 이름이나 일정 이름을 검색하세요" />
				<button type="button">검색하기</button>
				<div class="sort-area">
	       			<select name="sortOrder">
	        		    <option value="latest">날짜순</option>
	        		    <option value="name">이름순</option>
	     			</select>
	   			</div>
			</div>
   			<div class="edit-area">
					<button>수정하기</button>
			</div>
		</div>
	</div>

	<div class="container">
		<figure class="figure">
			<div>수영하려 가자 👥</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
		<figure class="figure">
			<div>내 일정1</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
		<figure class="figure">
			<div>내 일정2</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
		<figure class="figure">
			<div>언더더씨 👥</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
		<figure class="figure">
			<div>내 일정1(임시)</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
		<figure class="figure">
			<div>부산 풀코스</div>
			<a href="#" class="box-placeholder">이미지</a>
			<figcaption class="figure-caption">금원 수원 수영장</figcaption>
			<div>
				 2026-04-16 📍경기 안양
			</div>
		</figure>
	</div>
<div>

</div>

	</main>
</body>
</html>